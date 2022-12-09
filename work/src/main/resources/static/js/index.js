let param = {
    day: "2022",
    name: "liu",
    age: 24
}

document.getElementById("sendData").onclick = function (){
    $.ajax({
        type:"post",
        async:true,
        dataType:"text",
        url: "http://127.0.0.1:8085/work/getData",
        data:param,
        success:function(data){
            console.log(data)
        },
        error:function(){
        }
    });
}

document.getElementById("sendJson").onclick = function () {
    $.ajax({
        type: "post",
        async: true,
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        url: "http://127.0.0.1:8085/work/getJson",
        data: JSON.stringify(param),
        success: function (data) {
            console.log(data)
        },
        error: function () {
        }
    });
}

    document.getElementById("sendMap").onclick = function (){
        $.ajax({
            type:"post",
            async:true,
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            url: "http://127.0.0.1:8085/work/getMap",
            data:JSON.stringify(param),
            success:function(data){
                console.log(data)
            },
            error:function(){
            }
        });
}

document.getElementById("sendDataMap").onclick = function (){
    $.ajax({
        type:"post",
        async:true,
        dataType:"text",
        url: "http://127.0.0.1:8085/work/getDataforMap",
        data:param,
        success:function(data){
            console.log(data)
        },
        error:function(){
        }
    });
}