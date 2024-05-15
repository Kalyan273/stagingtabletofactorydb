<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KeyAssure - User created | userRegistration</title>
  <!--[if mso]><style type="text/css">body, table, td, a { font-family: Arial, Helvetica, sans-serif !important; }</style><![endif]-->
  <style>
.table1 {
border-collapse: collapse;
width: 300px;
}

.key {
font-size: small;
color: black;
background-color: rgb(171, 223, 251);
}

.value {

text-align: center;
font-size: small;
color: black;
}

.heding {
padding-left: 80px;
color: rgb(100, 91, 91);
}

.tbl2head {
font-size: x-small;
background-color: rgb(171, 223, 251);
color: black;
text-align: center;
padding: 10px;
}

td {
border-bottom: 1px solid #ddd;
padding: 10px;

}
</style>
</head>

<body style="font-family: Helvetica, Arial, sans-serif; margin: 0px; padding: 0px; background-color: #ffffff;">
  <table role="presentation"
    style="width: 100%; border-collapse: collapse; border: 0px; border-spacing: 0px; font-family: Arial, Helvetica, sans-serif; background-color: rgb(239, 239, 239);">
    <tbody>
      <tr>
        <td align="center" style="padding: 1rem 2rem; vertical-align: top; width: 100%;">
          <table role="presentation"
            style="max-width: 600px; border-collapse: collapse; border: 0px; border-spacing: 0px; text-align: left;">
            <tbody>
              <tr>
                <td style="padding: 40px 0px 0px;">
                  <div style="text-align: left;">
                    <div style="padding-bottom: 20px; display:flex;">

                      <h1 style="margin: 1rem 0">Factory KeyAssure LLC.<sup>&copy;</sup></h1>
                    </div>
                  </div>
                  <div style="padding: 20px; background-color: rgb(255, 255, 255);">
                    <div style="color: rgb(0, 0, 0); text-align: left;">
                      <p style="padding-bottom: 16px">Hi ${Name}, Welcome to Factory KeyAssure LLC. </p>
                      <p style="font-family:verdana" ,color="black,font-size=" 50px">Your account has been registered
                        successfully.</p>
                      <table class="table1" border="1">
                        <tr>
                          <td class="key"><b>Name</b></td>
                          <td class="value">${Name}</td>
                        </tr>
                        <tr>
                          <td class="key"><b>UserName</b> </td>
                          <td class="value">${username}</td>
                        </tr>
                        <tr>
                          <td class="key"><b>Street Address</b></td>
                          <td class="value">${streetAdr}</td>
                        </tr>

                      </table>
                      <p style="padding-bottom: 16px">Thanks,<br>Factory KeyAssure LLC.<sup>&copy;</sup></p>
                      <p style="padding-bottom: 16px; font-size:13px; font-style:italic;">This is an auto generated
                        email. Please do not reply to this email.</p>
                    </div>
                  </div>
                  <div style="padding-top: 20px; color: rgb(153, 153, 153); text-align: center;">
                    <p style="padding-bottom: 16px">Factory KeyAssure LLC.<sup>&copy;</sup></p>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </td>
      </tr>
    </tbody>
  </table>
</body>

</html>