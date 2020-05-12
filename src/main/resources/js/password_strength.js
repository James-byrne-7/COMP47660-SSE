function CheckPasswordStrength(password) {
    // Compute password strength to display below input
    let passwordStrength = document.getElementById("passwordStrength");

    // Leave blank if empty
    if (password.length == 0) {
        passwordStrength.innerHTML = "";
        return;
    }

    let regex = [];
    regex.push("[A-Z]"); //Uppercase Alphabet.
    regex.push("[a-z]"); //Lowercase Alphabet.
    regex.push("[0-9]"); //Digit.
    regex.push("[$@$!%*#?&^]"); //Special Character.
    regex.push("(?=.{8,32})");

    let testsPassed = 0;
    for (let i = 0; i < 5; i++) {
        if (new RegExp(regex[i]).test(password)) {
            testsPassed++;
        }
    }

    //Display status.
    let color = "";
    let strength = "";
    switch (testsPassed) {
        case 0:
        case 1:
            strength = "Very Weak";
            color = "darkred";
            break;
        case 2:
            strength = "Weak";
            color = "red";
            break;
        case 3:
            strength = "Fair";
            color = "darkorange";
            break;
        case 4:
            strength = "Strong";
            color = "green";
            break;
        case 5:
            strength = "Very Strong";
            color = "darkgreen";
            break;
    }
    passwordStrength.innerHTML = strength;
    passwordStrength.style.color = color;
    checkPasswordMatches();
}
