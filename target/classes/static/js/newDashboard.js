let modalHeader = $("#modalHeader");
let modalBody = $("#modalBody");
let modalFooter = $("#modalFooter");
let deleteBtn = $("#deleteBtn");
let saveBtn = $("#saveBtn");

$(document).ready(() => {
    ajaxRequest("/pet/allPets", "GET", null).then((response) => {
        petResponseToTable();
    });

});



$("#userTableBody").find("tr").click(function(){
    let user = {
        userId: $(this).find(".userId").text(),
        firstName: $(this).find(".firstName").text(),
        lastName: $(this).find(".lastName").text(),
        email: $(this).find(".email").text(),
        numberOfPets: $(this).find(".numberOfPets").text()
    };

    buildUserModal(user);

});

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
});

function buildUserModal(user){
    modalHeader.empty();
    modalBody.empty();

    modalHeader.append(user.userId);
    modalBody.append(`  <label for="firstName" style="display: block">First Name: </label>
                        <input type="text" value="${user.firstName}" id="firstName" style="display: block">
                        <label for="lastName" style="display: block">Last Name: </label>
                        <input type="text" value="${user.lastName}" id="lastName" style="display: block">
                        <label for="email" style="display: block">Email: </label>
                        <input type="email" value="${user.email}" id="email" style="display: block">
                        <label for="numberOfPets" style="display: block">Number of Pets: </label>
                        <input type="number" value="${user.numberOfPets}" id="numberOfPets" style="display: block">`);
}

function buildPetModal(pet){
    modalHeader.empty();
    modalBody.empty();

    modalHeader.append(pet.petId);
    modalBody.append(`  <label for="petName" style="display: block">Pet Name: </label>
                        <input type="text" value="${pet.petName}" id="petName" style="display: block">
                        <label for="birthdate" style="display: block">Birthdate: </label>
                        <input type="text" value="${pet.birthdate}" id="birthdate" style="display: block">
                        <label for="breedType" style="display: block">Breed Type: </label>
                        <select id="breedType">
                            <option value="${pet.breedType}" selected disabled hidden>${pet.breedType}</option>
                            <option value="alien">Alien</option>
                            <option value="bird">Bird</option>
                            <option value="bunny">Bunny</option>
                            <option value="cat">Cat</option>
                            <option value="dog">Dog</option>
                            <option value="fish">Fish</option>
                            <option value="horse">Horse</option>
                            <option value="monkey">Monkey</option>
                            <option value="sheep">Sheep</option>
                            <option value="snake">Snake</option>
                            <option value="turtle">Turtle</option>
                        </select>
                        <label for="sex" style="display: block">Sex: </label>
                        <form action="/action_page.php">
                              <input type="radio" id="male" name="fav_language" value="male">
                              <label for="male">Male</label>
                              <input type="radio" id="female" name="fav_language" value="female">
                              <label for="female">Female</label>
                        </form> 
                        <label for="color" style="display: block">Color: </label>
                        <input type="text" value="${pet.color}" id="color" style="display: block">
                        <label for="ownerId" style="display: block">Owner Id: </label>
                        <input type="text" value="${pet.userId}" id="ownerId" style="display: block">
                    `);
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
