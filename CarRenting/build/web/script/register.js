
function validform() {

        var a = document.forms["my-form"]["txtName"].value;
        var b = document.forms["my-form"]["txtEmail"].value;
        var c = document.forms["my-form"]["txtPhone"].value;
        var d = document.forms["my-form"]["txtAddress"].value;
        var e = document.forms["my-form"]["txtPassword"].value;
                

        if (a==null || a=="")
        {
            alert("Please Enter Your Full Name");
            return false;
        }else if (b==null || b=="")
        {
            alert("Please Enter Your Email Address");
            return false;
        }else if (c==null || c=="")
        {
            alert("Please Enter Your Phone Number");
            return false;
        }else if (d==null || d=="")
        {
            alert("Please Enter Your Present Address");
            return false;
        }else if (e==null || e=="")
        {
            alert("Please Enter Your Password");
            return false;
        }

    }
