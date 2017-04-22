<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <STYLE type=text/css>
        BODY {
            FONT-SIZE: 12px;
            COLOR: #ffffff;
            FONT-FAMILY: 宋体
        }

        TD {
            FONT-SIZE: 12px;
            COLOR: #ffffff;
            FONT-FAMILY: 宋体
        }

        .error {
            FONT-SIZE: 12px;
            COLOR: red;
            FONT-FAMILY: 宋体
        }
    </STYLE>

    <META content="MSHTML 6.00.6000.16809" name=GENERATOR>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

    <script type="text/javascript">
        function onCheckAcct() {
            $("#user_acct_hint").show();
            var acct = $("#user_acct").val();
            var acctHint = $("#user_acct_hint");
            if (acct.length == 0) {
                acctHint.addClass("error")
                acctHint.html("账户不能为空")
            } else {
                var url = "${pageContext.request.contextPath}/user_check_acct.action";
                var param = {"user_acct": acct};
                $.post(url, param, function (data) {
                    if (data == "ok") {
                        acctHint.removeClass("error")
                        acctHint.hide();
                    } else {
                        acctHint.addClass("error")
                        acctHint.html("账户不能为空")
                    }
                })
            }
        }

        function onCheckPwd() {
            $("#user_pwd_hint").show();
            var pwd = $("#user_pwd").val();
            var hint = $("#user_pwd_hint");
            if (pwd.length == 0) {
                hint.addClass("error")
                hint.html("密码不能为空")
            } else {
                hint.removeClass("error")
                hint.hide();
            }
        }

        function onCheckName() {
            $("#user_name_hint").show();
            var name = $("#user_name").val();
            var hint = $("#user_name_hint");
            if (name.length == 0) {
                hint.addClass("error")
                hint.html("用户名不能为空")
            } else {
                hint.removeClass("error")
                hint.hide();
            }
        }

        function onCleanAcct() {
            $("#user_acct_hint").hide()
        }
        function onCleanName() {
            $("#user_name_hint").hide()
        }
        function onCleanPwd() {
            $("#user_pwd_hint").hide()
        }

        function onCheckSubmit() {
            return $(".error").size > 0 ? false : true;
        }
    </script>
</HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath}/user_register.action" method=post>

    <DIV id=UpdatePanel1>
        <DIV id=div1
             style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
        <DIV id=div2
             style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


        <DIV>&nbsp;&nbsp;</DIV>
        <DIV>
            <TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
                <TBODY>
                <TR>
                    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif"
                                                   border=0></TD>
                </TR>
                <TR>
                    <TD background=images/login_2.jpg height=300>
                        <TABLE height=300 cellPadding=0 width=900 border=0>
                            <TBODY>
                            <TR>
                                <TD colSpan=2 height=35></TD>
                            </TR>
                            <TR>
                                <TD width=360></TD>
                                <TD>
                                    <TABLE cellSpacing=0 cellPadding=2 border=0>
                                        <TBODY>
                                        <TR>
                                            <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                                            <TD style="HEIGHT: 28px" width=150>
                                                <INPUT id="user_acct" style="WIDTH: 130px" onblur="onCheckAcct()"
                                                       onclick="onCleanAcct()"
                                                       name="user_acct">
                                            </TD>
                                            <TD style="HEIGHT: 28px" width=370><SPAN
                                                    id="user_acct_hint"
                                                    style="FONT-WEIGHT: bold;"></SPAN>
                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD style="HEIGHT: 28px">登录密码：</TD>
                                            <TD style="HEIGHT: 28px"><INPUT id="user_pwd" style="WIDTH: 130px"
                                                                            onclick="onCleanPwd()"
                                                                            onblur="onCheckPwd()"
                                                                            type=password name="user_pwd"></TD>
                                            <TD style="HEIGHT: 28px"><SPAN id="user_pwd_hint"
                                                                           style="FONT-WEIGHT: bold"></SPAN>
                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD style="HEIGHT: 28px">用 户 名：</TD>
                                            <TD style="HEIGHT: 28px"><INPUT id="user_name"
                                                                            onclick="onCleanName()"
                                                                            onblur="onCheckName()"
                                                                            style="WIDTH: 130px" name="user_name"></TD>
                                            <TD style="HEIGHT: 28px"><SPAN id="user_name_hint"
                                                                           style="FONT-WEIGHT: bold;"></SPAN>
                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD style="HEIGHT: 18px"></TD>
                                            <TD style="HEIGHT: 18px"></TD>
                                            <TD style="HEIGHT: 18px"></TD>
                                        </TR>
                                        <TR>
                                            <TD><INPUT id=btn type="submit" value="注册" onclick="onCheckSubmit()"></TD>
                                            <TD>
                                                <a href="login.jsp">登陆</a>
                                            </TD>
                                        </TR>
                                        </TBODY>
                                    </TABLE>
                                </TD>
                            </TR>
                            </TBODY>
                        </TABLE>
                    </TD>
                </TR>
                <TR>
                    <TD><IMG src="images/login_3.jpg"
                             border=0></TD>
                </TR>
                </TBODY>
            </TABLE>
        </DIV>
    </DIV>


</FORM>
</BODY>
</HTML>


</body>
</html>