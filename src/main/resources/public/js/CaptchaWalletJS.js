const captcha = document.querySelector(".captcha"),
    reloadBtn = document.querySelector(".reload-btn"),
    inputField = document.querySelector(".input-area input"),
    checkBtn = document.querySelector(".check-btn"),
    statusTxt = document.querySelector(".status-text");

//storing all captcha characters in array
let allCharacters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
    'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
    't', 'u', 'v', 'w', 'x', 'y', 'z', 0, 1, 2, 3, 4, 5, 6, 7, 8, 9];

function getCaptcha() {
    for (let i = 0; i < 6; i++) { //getting 6 random characters from the array
        let randomCharacter = allCharacters[Math.floor(Math.random() * allCharacters.length)];
        captcha.innerText += ` ${randomCharacter}`; //passing 6 random characters inside captcha innerText
    }
}

getCaptcha(); //calling getCaptcha when the page open
//calling getCaptcha & removeContent on the reload btn click
reloadBtn.addEventListener("click", () => {
    removeContent();
    getCaptcha();
});

checkBtn.addEventListener("click", e => {
    e.preventDefault(); //preventing button from it's default behaviour
    statusTxt.style.display = "block";
    //adding space after each character of user entered values because I've added spaces while generating captcha
    let inputVal = inputField.value.split('').join(' ');
    if (inputVal == captcha.innerText) { //if captcha matched

        let offerId = $("#offer-id").val();

        $.ajax({
            url: "http://localhost:8080/api/customer/walletPay/" + offerId,
            type: "GET",
            success: function (data) {
                alert("nice shit.");
                location.href = "http://localhost:63342/FrontTest/.idea/src/web/index.html?_ijt=l52ube96mjhg4sojplb4vjvj45&_ij_reload=RELOAD_ON_SAVE"
            },
            error: function (error) {
                console.log(error);
            },
        })


    } else {
        statusTxt.style.color = "#ff0000";
        statusTxt.innerText = "Captcha not matched. Please try again!";
        setTimeout(() => { //calling removeContent & getCaptcha after 4 seconds
            removeContent();
            getCaptcha();
        }, 2000);
    }
});

function removeContent() {
    inputField.value = "";
    captcha.innerText = "";
    statusTxt.style.display = "none";
}

window.onload = function onloadPage() {
    setTimeout("window.close()", 600000)
}
