let email = $("#email");
let userPw = $("#userPassword");
let submitBtn = $("#submit");

submitBtn.click(() => {
    signInRequest(email.val(), userPw.val());
});


function signInRequest(email, password){
   let signIn = {
      email: email,
      password: password
   };
   ajaxRequest("http://localhost:8080/auth/login", "POST", signIn).then((response) => {
      console.log(response);
      window.location.href = "/secure/dashboard";
   }).catch((error) => {
      alert(JSON.stringify(error));
   });

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