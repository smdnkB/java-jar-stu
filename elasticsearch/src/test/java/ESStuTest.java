import com.alibaba.fastjson.JSONObject;
import com.liu.App;
import com.liu.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ESStuTest {

    private RestHighLevelClient esClient; // 声明es客户端

    /**
     * 初始化es客户端
     */
    @Before
    public void initEs(){
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.0.100",9200,"http"));
        esClient = new RestHighLevelClient(restClientBuilder);
    }

    /**
     * 新建索引
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    //=========================文档操作======================
    /**
     * 向指定索引添加一条指定了id的文档
     */
    @Test
    public void intoDoc() throws IOException {
        User user = new User();
        user.setName("liu");
        user.setAge(24);
        String userJSON = JSONObject.toJSONString(user);

        IndexRequest request = new IndexRequest();
        request.index("user").id("user"+System.currentTimeMillis());
        request.source(userJSON, XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
    }
    /**
     * 修改指定索引指定文档的内容
     */
    @Test
    public void updateDoc() throws IOException {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("user-1001");;
        updateRequest.doc(XContentType.JSON,"name","liu");

        UpdateResponse updateResponse = esClient.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(updateResponse.getResult());

    }
    /**
     * 获取文档内容
     * 查询指定索引内指定文档的数据内容
     */
    @Test
    public void getDoc() throws IOException {
        GetRequest getRequest = new GetRequest();
        getRequest.index("user").id("user-1001");
        GetResponse getResponse = esClient.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(getResponse.getSourceAsString());

    }
    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index("user").id("user-1001");
        DeleteResponse deleteResponse = esClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(deleteResponse.getResult());

    }

    /**
     * 批量新增文档
     */
    @Test
    public void intoDocPatch() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest().index("user").id("user-1001").source(JSONObject.toJSONString(new User()),XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("user").id("user-1002").source(JSONObject.toJSONString(new User()),XContentType.JSON));
        BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.getTook());
    }

    /**
     * 批量删除文档
     */
    @Test
    public void deleteDocPatch() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new DeleteRequest().index("user").id("user-1001"));
        bulkRequest.add(new DeleteRequest().index("user").id("user-1002"));
        BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.getTook());
    }

    //=====================查询====================
    /**
     * es match 查询 + 分页
     */
    @Test
    public void search() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        // 指定查询索引
        searchRequest.indices("user");
        // 构建查询参数
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 分页
        query.from(0);
        query.size(10);
        // 排序
        query.sort("age", SortOrder.ASC);

        // 结果过滤
        String[] include = {"name"};
        String[] exclude = {};
        query.fetchSource(include,exclude);

        searchRequest.source(query);
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);

        // 查询匹配结果数据
        SearchHits hits = searchResponse.getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
        // 查询耗时
        System.out.println(searchResponse.getTook());
    }

    /**
     * es 条件 查询
     */
    @Test
    public void searchTerm() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        // 指定查询索引
        searchRequest.indices("user");
        // 构建查询参数
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.termQuery("age",20));

        searchRequest.source(query);
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);

        // 查询匹配结果数据
        SearchHits hits = searchResponse.getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
        // 查询耗时
        System.out.println(searchResponse.getTook());
    }

    /**
     * es 组合条件 查询
     */
    @Test
    public void searchTerms() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        // 指定查询索引
        searchRequest.indices("user");
        // 构建查询参数
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("age",20));
        boolQueryBuilder.must(QueryBuilders.termQuery("age",30));

        SearchSourceBuilder query = new SearchSourceBuilder().query(boolQueryBuilder);
        query.from(0);
        query.size(10);

        searchRequest.source(query);
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);

        // 查询匹配结果数据
        SearchHits hits = searchResponse.getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
        // 查询耗时
        System.out.println(searchResponse.getTook());
    }

    /**
     * es 范围 查询
     */
    @Test
    public void searchRange() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        // 指定查询索引
        searchRequest.indices("user");
        // 构建查询参数
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.rangeQuery("age").lte(20));
        boolQueryBuilder.must(QueryBuilders.termQuery("name","fei"));

        SearchSourceBuilder query = new SearchSourceBuilder().query(boolQueryBuilder);
        query.from(0);
        query.size(10);

        searchRequest.source(query);
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);

        // 查询匹配结果数据
        SearchHits hits = searchResponse.getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
        // 查询耗时
        System.out.println(searchResponse.getTook());
    }

    /**
     * 关闭es客户端
     */
    @After
    public void closeEs() throws IOException {
        esClient.close();
    }
}
