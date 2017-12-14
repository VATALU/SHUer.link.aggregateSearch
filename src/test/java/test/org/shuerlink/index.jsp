<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;no-cache">

    <title>Insert title here</title>
</head>
<body>
<form action="/search/webpage">
    webpage:<input type="text" name="keyword"> <input type="submit"
                                                      value="搜索">
</form>
<form action="/search/imageUrl">
    imageUrl:<input type="text" name="keyword"> <input type="submit"
                                                       value="搜索">
</form>
<form action="/search/vedio">
    vedio:<input type="text" name="keyword"> <input type="submit"
                                                    value="搜索">
</form>
<form action="/search/article">
    article:<input type="text" name="keyword"> <input type="submit" value="搜索">
</form>
<form action="/login" method="post">
    client:<input type="text" name="userName"><input type="password" name="password">
    <input type="submit" value="登陆">
</form>
<form action="/api" method="post">
    api:<textarea name="login" id="" cols="30" rows="10"></textarea>
    <input type="submit" value="查询">
</form>
</body>
</html>