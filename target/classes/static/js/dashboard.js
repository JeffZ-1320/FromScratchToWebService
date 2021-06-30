$("#getAllButton").click(() => {
    validateCheckbox(getAllUsers, getAllPets);
});

$("#getByIdButton").click(() => {
   validateCheckbox(preGetUserById, preGetPetById);
});

$("#addButton").click(() => {
    validateCheckbox(preAddNewUser, preAddNewPet);
})

$("#updateButton").click(() => {
    validateCheckbox(preUpdateUser, preUpdatePet);
});

$("#deleteButton").click(() => {
    validateCheckbox(preDeleteUser, preDeletePet);
});

$("#signOut").click(() => {
    // document.cookie = "test=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    ajaxRequest("http://localhost:8080/auth/deleteCookie", "POST", "{}").then((response) => {
        console.log(response);
    }).catch((error) => {
        alert(error);
    });

});

function validateCheckbox(userFunction, petFunction){
    if($("#selectUser").prop("checked") && $("#selectPet").prop("checked")){
        userFunction();
        petFunction();
    }else if($("#selectPet").prop("checked")){
        petFunction();
    }else if($("#selectUser").prop("checked")){
        userFunction();
    }else{
        alert("At least one of the box must be checked");
    }
}

function getAllPets(){
    ajaxRequest("http://localhost:8080/pet/allPets", "GET", null).then((response) => {
        // console.log("Get all success: " + JSON.stringify(response));
        $("#petBody").empty();
        petResponseToTable(response);
    });
}

function preGetPetById(){
    getPetById($("#petId").val());

}

function getPetById(id){
    ajaxRequest("http://localhost:8080/pet/getPetById/" + id, "GET", null).then((response) => {
        $("#petBody").empty();
        // console.log(response);
        petResponseToTable([response]);
    });

}

function preAddNewPet(){
    const newPet = {
        // petId: $("#petId").val(),
        petName: $("#petName").val(),
        birthdate: $("#birthdate").val(),
        breedType: $("#breedType").val(),
        sex: $("#sex").val(),
        color: $("#color").val(),
        userId: $("#ownerId").val()
    };
    addNewPet(newPet);
}

function addNewPet(newPet){
    // JSON stringify to convert js object to json string
    ajaxRequest("http://localhost:8080/pet/newPet", "POST", newPet).then((response) => {
       console.log(response);
    });
}

function preUpdatePet(){
    const updatedPet = {
        petId: $("#petId").val(),
        petName: $("#petName").val(),
        birthdate: $("#birthdate").val(),
        breedType: $("#breedType").val(),
        sex: $("#sex").val(),
        color: $("#color").val(),
        userId: $("#ownerId").val()
    };
    updatePet(updatedPet);
}

function updatePet(updatedPet){
    ajaxRequest("http://localhost:8080/pet/updatePet/" + updatedPet.petId, "PUT", updatedPet).then((response) => {
        console.log(response);
    });
}

function preDeletePet(){
    deletePet($("#petId").val());
}

function deletePet(id){
    ajaxRequest("http://localhost:8080/pet/deletePet/" + id, "DELETE", null).then((response) => {
        console.log(response);
    });
}

function getAllUsers(){
    ajaxRequest("http://localhost:8080/user/allUsers", "GET", null).then((response) => {
        $("#userBody").empty();
        userResponseToTable(response);
    });
}

function preGetUserById(){
    getUserById($("#userId").val());
}

function getUserById(id){
    ajaxRequest("http://localhost:8080/user/getUserById/" + id, "GET", null).then((response) => {
        $("#userBody").empty();
        userResponseToTable([response]);
    });
}

function preAddNewUser(){
    const newUser = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        numberOfPets: $("#numberOfPets").val()
    };
    addNewUser(newUser);
}

function addNewUser(newUser){
    ajaxRequest("http://localhost:8080/user/newUser", "POST", newUser).then((response) => {
       console.log(response);
    });
}

function preUpdateUser(){
    const updatedUser = {
        userId: $("#userId").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        numberOfPets: $("#numberOfPets").val()
    };
    updateUser(updatedUser);
}

function updateUser(updatedUser){
    ajaxRequest("http://localhost:8080/user/updateUser/" + updatedUser.id, "PUT",updatedUser)
        .then((response) => {
           console.log(response);
        });
}

function preDeleteUser(){
    deleteUser($("#userId").val());
}

function deleteUser(id){
    ajaxRequest("http://localhost:8080/user/deleteUser/" + id, "DELETE", null).then((response) => {
        console.log(response);
    });
}

function ajaxRequest(url, method, data) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: url,
            type: method,
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: (response) => {
                resolve(response);
            }, error: (error) => {
                reject(error);
            }
        });
    });
}

function petResponseToTable(response){
    // const petArray = JSON.parse(response);
    // console.log(petArray);
    // console.log(response[0].petName);
    for (let i = 0; i < response.length; i++) {
        $("#petBody").append("<tr>" +
            "<td>" + response[i].petId + "</td>" +
            "<td>" + response[i].petName + "</td>" +
            "<td>" + response[i].birthdate + "</td>" +
            // need regex for birthdate
            "<td>" + response[i].breedType + "</td>" +
            "<td>" + response[i].sex + "</td>" +
            "<td>" + response[i].color+ "</td>" +
            "<td>" + response[i].userId + "</td>"
        );

    }
}

function userResponseToTable(response){
    // const petArray = JSON.parse(response);
    // console.log(petArray);
    // console.log(response[0].petName);
    for (let i = 0; i < response.length; i++) {
        $("#userBody").append("<tr>" +
            "<td>" + response[i].userId + "</td>" +
            "<td>" + response[i].firstName + "</td>" +
            "<td>" + response[i].lastName + "</td>" +
            "<td>" + response[i].numberOfPets + "</td>"
        );

    }
}

