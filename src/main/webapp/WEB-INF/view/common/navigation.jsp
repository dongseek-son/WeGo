<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
  body {
    padding: 0px;
    margin: 0px;
    font-size: 9pt;
  }
  nav {
    background-color: #ddd;
    padding: 15px;
  }

  nav ul {
    padding: 0px;
    margin: 0px;
  }

  nav ul > li {
    display: inline-block;
    margin-left: 30px;
  }

  nav ul > li:first-child {
    margin-left: 0px;
  }

  a, a:visited {
    text-decoration: none;
    color: #333;
  }

  a:active {
    text-decoration: underline;
  }

  a:hover {
    color: #999;
  }
</style>
<nav>
  <ul>
  	<li><a href="/WeGo/mygoal/write">WriteGoal</a></li>
  	<li><a href="/WeGo/message/write">WriteMessage</a></li>
    <li><a href="/WeGo/message/receivelist">ReceiveList</a></li>
    <li><a href="/WeGo/message/sendlist">SendList</a></li>
    <li><a href="/WeGo/member/login">Login</a></li>
    <li><a href="/WeGo/member/regist">Regist</a></li>
    <li><a href="/WeGo/memberlogout">Logout</a></li>
  </ul>
</nav>
