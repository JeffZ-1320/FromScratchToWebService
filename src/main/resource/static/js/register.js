let pw = $("#pw");
let pwConfirm = $("#pwConfirm");
let email = $("#email");
let firstName = $("#firstName");
let lastName = $("#lastName");
let registerBtn = $("#registerBtn");

email.keyup(() => {
    if(isValidateEmail()){
        $("#emailStatus").html("<span class=\"badge rounded-pill bg-success\">Success</span>");
    }else{
        $("#emailStatus").html("<span class=\"badge rounded-pill bg-danger\">Invalid Email</span>");
    }
});

pwConfirm.keyup(() => {
    if(isPasswordMatched()){
        $("#pwStatus").html("<span class=\"badge rounded-pill bg-success\">Success</span>");
    }else{
        $("#pwStatus").html("<span class=\"badge rounded-pill bg-danger\">Password does not match</span>");
    }
});

registerBtn.click(() => {
    if(isPasswordMatched() && isValidateEmail()){
        let newUser = {
            firstName: firstName.val(),
            lastName: lastName.val(),
            email: email.val(),
            // probably should encrypt the password before sending the request
            password: pw.val()
        }
        ajaxRequest("http://localhost:8080/auth/register", "POST", newUser).then((response) => {
            console.log(response);
            // window.location.replace("http://localhost:8080/secure/dashboard");
        }).catch((error) => {
            alert(error);
        });
    }else{
        alert("At least one register criteria was not met")
    }
});

function isValidateEmail(){
    if(email.val() !== ""){
        let regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email.val());
        // console.log(regex.test(email.val()));
    }else{
        return false;
    }
}

function isPasswordMatched() {
    return pwConfirm.val() === pw.val();
}

function ajaxRequest(url, method, data){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: url,
            type: method,
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType:"json",
            success: (response) => {
                resolve(response);
            },
            error: (error) => {
                reject(error);
            }
        });
    });
}