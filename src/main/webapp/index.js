$(document).ready(function() {
    getMessage();
});

function getMessage() {
    $.ajax({
        type : "POST",
        url : "GetAllMessage",
        dataType : "json",
        success : function(data) {
            $.cookie('latest_id', data[0].id);
            var item = "<tbody>";
            for (var i in data) {
                var timeagoIns = timeago();
                var time = timeagoIns.format(new Date(parseInt(data[i].timestamp)), 'zh_CN');
                //var time = jQuery.timeago(timestamp);
                item += "<tr><td><p><span>"+data[i].author+
                    "</span><span style='float:right;'>"+time+
                    "</span></p><p>"+data[i].content+"</p>";
                if (data[i].image.length >= 1) {
                    item += "<p><img alt=': )' src='"+data[i].image+"' height='100'/></p>";
                }
                item += "</td></tr>";
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
    var latest_id = $.cookie('latest_id');
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
                var timeagoIns = timeago();
                var time = timeagoIns.format(new Date(parseInt(data[i].timestamp)), 'zh_CN');
                item += "<tr><td><p><span>"+data[i].author+
                    "</span><span style='float:right;'>"+time+
                    "</span></p><p>"+data[i].content+"</p>";
                if (data[i].image.length >= 1) {
                    item += "<p><img alt=': )' src='"+data[i].image+"' height='100'/></p>";
                }
                item += "</td></tr>";
            }
            item += "</tbody>";
            $('#timeline-content').prepend(item);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
