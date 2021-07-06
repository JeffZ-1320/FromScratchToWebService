let modalHeader = $("#modalHeader");
let modalBody = $("#modalBody");
let modalFooter = $("#modalFooter");
let deleteBtn = $("#deleteBtn");
let saveBtn = $("#saveBtn");
let newBtn = $("#newBtn");

$(document).ready(() => {
    refreshUserTable()
    refreshPetTable()
});

$("#addUser").click(() => {
    let newUser = {
        userId: "Add new user",
        firstName: "",
        lastName: "",
        email: "",
        numberOfPets: ""
    };
    buildUserModal(newUser);
    userNewBtnListener();
});

$("#addPet").click(() => {

    let newPet = {
        petId: "Add new pet",
        petName: "",
        birthdate: "",
        breedType: "",
        sex: "",
        color: "",
        userId: ""
    };
    buildPetModal(newPet);
    petNewBtnListener()
});

function userNewBtnListener(){
    newBtn.off("click");
    newBtn.click(() => {
        let newUser = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            numberOfPets: $("#numberOfPets").val(),
            password: $("#password").val(),
        };
        addNewUser(newUser).then((response) => {
            console.log(response);
            refreshUserTable();
        }).catch((error) => {
            console.log(error);
        });
    });
}

function petNewBtnListener(){
    newBtn.off("click");
    newBtn.click(() => {
        let newPet = {
            petName: $("#petName").val(),
            birthdate: $("#birthdate").val(),
            breedType: $("#breedType").val(),
            sex: $("input[name=gender]:checked", "#genderDiv").val(),
            color: $("#color").val(),
            userId: $("#ownerId").val()
        };
        addNewPet(newPet).then((response) => {
            console.log(response);
            refreshPetTable();
        }).catch((error) => {
            console.log(error);
        });
    });
}

function userUpdateBtnListener(){
    saveBtn.off("click");
    saveBtn.click(() => {
        let user = {
            userId: $("#userId").text(),
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            numberOfPets: $("#numberOfPets").val(),
            password: $("#password").val()
        };
        updateUser(user).then((response) => {
            console.log(response);
            refreshUserTable();
        }).catch((error) => {
            console.log(error);
        });
    });
}

function petUpdateBtnListener(){
    saveBtn.off("click");
    saveBtn.click(() => {
        let pet = {
            petId: $("#petId").text(),
            petName: $("#petName").val(),
            birthdate: $("#birthdate").val(),
            breedType: $("#breedType").val(),
            sex: $("input[name=gender]:checked", "#genderDiv").val(),
            color: $("#color").val(),
            userId: $("#ownerId").val()
        };
        updatePet(pet).then((response) => {
            console.log(response);
            refreshPetTable();
        }).catch((error) => {
            console.log(error);
        });
    });
}

function userDeleteBtnListener(){
    deleteBtn.off("click");
    deleteBtn.click(() => {
        let user = {
            userId: $("#userId").text(),
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            numberOfPets: $("#numberOfPets").val(),
            password: $("#password").val()
        };
        deleteUser(user).then((response) => {
            console.log(response);
            refreshUserTable();
        }).catch((error) => {
            console.log(error);
        });
    });
}

function petDeleteBtnListener(){
    deleteBtn.off("click");
    deleteBtn.click(() => {
        let pet = {
            petId: $("#petId").text(),
            petName: $("#petName").val(),
            birthdate: $("#birthdate").val(),
            breedType: $("#breedType").val(),
            sex: $("input[name=gender]:checked", "#genderDiv").val(),
            color: $("#color").val(),
            userId: $("#ownerId").val()
        };
        deletePet(pet).then((response) => {
            console.log(response);
            refreshPetTable();
        }).catch((error) => {
            console.log(error);
        });
    });
}

function userModalListener(){
        $("#userTableBody").find("tr").off("click");
        $("#userTableBody").find("tr").click(function(){
        let user = {
            userId: $(this).find(".userId").text(),
            firstName: $(this).find(".firstName").text(),
            lastName: $(this).find(".lastName").text(),
            email: $(this).find(".email").text(),
            numberOfPets: $(this).find(".numberOfPets").text()
        };
        buildUserModal(user);
        userUpdateBtnListener()
        userDeleteBtnListener()
    });
}

function petModalListener(){
    $("#petTableBody").find("tr").off("click");
    $("#petTableBody").find("tr").click(function(){
        let pet = {
            petId: $(this).find(".petId").text(),
            petName: $(this).find(".petName").text(),
            birthdate: $(this).find(".birthdate").text(),
            breedType: $(this).find(".breedType").text(),
            sex: $(this).find(".sex").text(),
            color: $(this).find(".color").text(),
            userId: $(this).find(".ownerId").text()
        }

        buildPetModal(pet)
        petUpdateBtnListener();
        petDeleteBtnListener()
    });
}

function buildUserModal(user){
    modalHeader.empty();
    modalBody.empty();

    modalHeader.append(`<h5 class="modal-title" id="userId">${user.userId}</h5>`);
    modalBody.append(`  <label for="firstName" style="display: block">First Name: </label>
                        <input type="text" value="${user.firstName}" id="firstName" style="display: block">
                        <label for="lastName" style="display: block">Last Name: </label>
                        <input type="text" value="${user.lastName}" id="lastName" style="display: block">
                        <label for="email" style="display: block">Email: </label>
                        <input type="email" value="${user.email}" id="email" style="display: block">
                        <label for="numberOfPets" style="display: block">Number of Pets: </label>
                        <input type="number" value="${user.numberOfPets}" id="numberOfPets" style="display: block">  
                        <label for="password" style="display: block">Password: </label>
                        <input type="password" value="" id="password" style="display: block" placeholder="Required">
    `);
}

function buildPetModal(pet){
    modalHeader.empty();
    modalBody.empty();

    modalHeader.append(`<h5 class="modal-title" id="petId">${pet.petId}</h5>`);
    modalBody.append(`  <label for="petName" style="display: block">Pet Name: </label>
                        <input type="text" value="${pet.petName}" id="petName" style="display: block">
                        <label for="birthdate" style="display: block">Birthdate: </label>
                        <input type="text" value="${pet.birthdate}" id="birthdate" style="display: block">
                        <label for="breedType" style="display: block">Breed Type: </label>
                        <select id="breedType">
                            <option value="${pet.breedType}" selected disabled hidden>${pet.breedType}</option>
                            <option value="Alien">Alien</option>
                            <option value="Bird">Bird</option>
                            <option value="Bunny">Bunny</option>
                            <option value="Cat">Cat</option>
                            <option value="Dog">Dog</option>
                            <option value="Fish">Fish</option>
                            <option value="Horse">Horse</option>
                            <option value="Monkey">Monkey</option>
                            <option value="Sheep">Sheep</option>
                            <option value="Snake">Snake</option>
                            <option value="Turtle">Turtle</option>
                        </select>
                        <label for="sex" style="display: block">Sex: </label>
                        <div id="genderDiv">
                              <input type="radio" id="male" name="gender" value="Male">
                              <label for="male">Male</label>
                              <input type="radio" id="female" name="gender" value="Female">
                              <label for="female">Female</label>
                        </div> 
                        <label for="color" style="display: block">Color: </label>
                        <input type="text" value="${pet.color}" id="color" style="display: block">
                        <label for="ownerId" style="display: block">Owner Id: </label>
                        <input type="text" value="${pet.userId}" id="ownerId" style="display: block">
    `);

    if(pet.sex === "Male"){
        $("#male").prop("checked", true);
    }else if(pet.sex === "Female"){
        $("#female").prop("checked", true);
    }
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

function userResponseToTable(response){
    // const petArray = JSON.parse(response);
    // console.log(petArray);
    // console.log(response[0].petName);
    $("#userTableBody").empty();
    for (let i = 0; i < response.length; i++) {
        $("#userTableBody").append("<tr data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">" +
            "<td class=\"userId\">" + response[i].userId + "</td>" +
            "<td class=\"firstName\">" + response[i].firstName + "</td>" +
            "<td class=\"lastName\">" + response[i].lastName + "</td>" +
            "<td class=\"email\">" + response[i].email + "</td>" +
            "<td class=\"numberOfPets\">" + response[i].numberOfPets + "</td>" +
            "</tr>"
        );

    }
}

function petResponseToTable(response){
    // const petArray = JSON.parse(response);
    // console.log(petArray);
    // console.log(response[0].petName);
    $("#petTableBody").empty();
    for (let i = 0; i < response.length; i++) {
        $("#petTableBody").append("<tr data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">" +
            "<td class=\"petId\">" + response[i].petId + "</td>" +
            "<td class=\"petName\">" + response[i].petName + "</td>" +
            "<td class=\"birthdate\">" + response[i].birthdate + "</td>" +
            // need regex for birthdate
            "<td class=\"breedType\">" + response[i].breedType + "</td>" +
            "<td class=\"sex\">" + response[i].sex + "</td>" +
            "<td class=\"color\">" + response[i].color+ "</td>" +
            "<td class=\"ownerId\">" + response[i].userId + "</td>" +
            "<tr>"
        );
    }
}

function refreshUserTable(){
    ajaxRequest("http://localhost:8080/secure/user/allUsers", "GET", null).then((response) => {
        userResponseToTable(response);
        userModalListener();
    });
}

function refreshPetTable(){
    ajaxRequest("http://localhost:8080/secure/pet/allPets", "GET", null).then((response) => {
        petResponseToTable(response);
        petModalListener();
    });
}

function addNewUser(newUser){
    return ajaxRequest("http://localhost:8080/secure/user/newUser", "POST", newUser);
}

function addNewPet(newPet){
    return ajaxRequest("http://localhost:8080/secure/pet/newPet", "POST", newPet);
}

function updateUser(user){
    return ajaxRequest("http://localhost:8080/secure/user/updateUser/" + user.userId, "PUT", user)
}

function updatePet(pet){
    return ajaxRequest("http://localhost:8080/secure/pet/updatePet/" + pet.petId, "PUT", pet)
}

function deleteUser(user){
    return ajaxRequest("http://localhost:8080/secure/user/updateUser/" + user.userId, "PUT", user)
}

function deletePet(pet){
    return ajaxRequest("http://localhost:8080/secure/pet/deletePet/" + pet.petId, "PUT", pet)

}