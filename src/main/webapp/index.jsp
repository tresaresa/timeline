<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>

<script>
    $(document).ready(function() {
        getMessage();
    });

    function getMessage() {
        $.ajax({
            type : "POST",
            url : "GetAllMessage",
            dataType : "json",
            success : function(data) {
                var item = "<tbody>";
                for (var i in data) {
                    item += "<tr><td><p><span>"+data[i].author+
                        "</span><span>"+""+
                        "</span></p><p>"+data[i].content+"</p></td></tr>";
                }
                item += "</tbody>"
                $("#timeline-content").append(item);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    function getMore() {
        var top = document.getElementById('timeline-inner-wrapper').scrollTop;
        document.getElementById('timeline-inner-wrapper').scrollTo(0, top + 300);
    }

    function getNew() {
        var latest_id = $("#timeline-content").children("td:first-child").text()
        alert("id " + latest_id)
        $.ajax({
            type : "POST",
            url : "GetNewMessage",
            dataType : "json",
            data : {
                "latest_id" : latest_id
            },
            success : function(data) {
                var item = "<tbody>";
                for (var i in data) {
                    item += "<tr><td><p><span>"+data[i].author+
                        "</span><span>"+""+
                        "</span></p><p>"+data[i].content+"</p></td></tr>";
                }
                item += "</tbody>"
                $("#timeline-content").append(item);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    function test() {
        $.cookie('name', '1');
        var n = $.cookie('name');
        alert(n);
    }

</script>

<style>
#timeline-content{
    border: 0;
    font-size: 12px;
}
#timeline-content tr td {
    border: 1px dashed #66CCFF;
    border-left: 0;
    border-right: 0;
    border-bottom: 0;
}
#timeline-content tr:last-child td {
    border-bottom: 0;
}
#timeline-content tr:first-child td {
    border-top: 0;
}

#timeline-head {
    font-size: 20px;
    padding: 15px;
    border-bottom: 1px solid #66CCFF;
}
#timeline-foot {
    border-top: 1px solid #66CCFF;
    text-align: center;
    border-top: 0;
}

#timeline-inner-wrapper {
    width: 100%;
    height:70%;
    overflow-y: scroll;
}

#btn-update {
    margin-left: 60%;
    border: 1px solid #66CCFF;
}
#btn-more {
    border: 1px solid #66CCFF;
    margin-top: 5%;
}

.bootstrap-table {
    border-radius: 3px;
    width: 25%;
    height: 60%;
}
.timeline-wrapper {
    border: 1px solid #66CCFF;
    position: absolute;
    right: 35%;
    top: 15%;
}

#timeline-inner-wrapper::-webkit-scrollbar {
    border-radius: 3px;
    width: 12px;
}
#timeline-inner-wrapper::-webkit-scrollbar-thumb {
    border-radius: 3px;
    -webkit-box-shadow: inset 0 0 0px rgba(0,0,0,0);
    background: white;
    border: 1px solid #66CCFF
}
#timeline-inner-wrapper::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 0px rgba(0,0,0,0);
}
</style>

</head>

<body>

<div class="container">
    <div class="bootstrap-table timeline-wrapper">
        <p id="timeline-head"><b>Timeline</b><input class="btn btn-default" type="button" value="更新" id="btn-update" onclick="test()"></p>
        <div id="timeline-inner-wrapper">
            <table class="table table-hover" id="timeline-content">
            </table>
        </div>
        <p id="timeline-foot"><input class="btn btn-default" type="button" value="更多" id="btn-more" onclick="getMore()"></p>
    </div>
</div>

</body>

</html>
