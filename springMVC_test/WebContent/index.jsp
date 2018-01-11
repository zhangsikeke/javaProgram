<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用戶登陸</title>

<style type="text/css">  
.STYLE3 {font-size: 9pt}   
</style>  

</head>
<body>
	<center>  
    <span class="STYLE3">用户登录</span>  
  </center>  
    <form method="post" action="login.do">
    <center>${map.error }</center>  
    <table align="center">  
        <tr>  
            <td height="23"><span class="STYLE3">输入用户名：</span></td>  
          <td height="23"><input name="uName" type="text"></td>  
        </tr>  
        <tr>  
            <td height="23"><span class="STYLE3">输入密码：</span></td>  
          <td height="23"><input name="psw" type="password"></td>  
        </tr>  
        <tr>  
            <td height="23" colspan="2" align="center">  
                <input type="submit" value="登录">  
                <input type="reset" value="重置">  
            </td>  
        </tr>  
    </table>  
    </form>
  </body>  
</body>
</html>