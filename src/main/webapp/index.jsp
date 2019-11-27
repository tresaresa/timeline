<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/timeago.js/3.0.2/timeago.js"></script>
<script src="index.js"></script>
<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
<link rel="stylesheet" href="index.css"/>


<script>

</script>

<style>

</style>

</head>

<body>
<div class="container">
    <div class="bootstrap-table timeline-wrapper">
        <p id="timeline-head"><b>Timeline</b><input class="btn btn-default" type="button" value="更新" id="btn-update" onclick="getNew()"></p>
        <div id="timeline-inner-wrapper">
            <table class="table table-hover" id="timeline-content">
            </table>
        </div>
        <p id="timeline-foot"><input class="btn btn-default" type="button" value="更多" id="btn-more" onclick="getMore()"></p>
    </div>
</div>

</body>

</html>
