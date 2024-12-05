function getUsers() {
    fetch('/api/users')
        .then(response => response.json()) 
        .then(data => {
            alert(JSON.stringify(data, null, 2)); 
        })
        .catch(error => {
            console.error('Greška pri dobijanju korisnika:', error);
        });
}

function login() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let data = {
        username: username,
        password: password
    };

    fetch('/api/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // Indicate that we are sending JSON
        },
        body: JSON.stringify(data) // Send data as JSON string
    })
    .then(response => response.text()) // Get the response as plain text
    .then(responseText => {
        try {
            // Try parsing the response as JSON
            let data = JSON.parse(responseText);
            
            // If data is successfully parsed, handle it as a successful login
            localStorage.setItem('name', data.name);
            localStorage.setItem('username', data.username);
            localStorage.setItem('role', data.role);

            window.location.href = 'home.html';
        } catch (error) {
            // If parsing fails, assume it's a plain error message
            alert('Error: ' + responseText); // Display the error message returned by the API
            console.log('Error: ', responseText); // Log the error to the console for debugging
        }
    })
    .catch(error => {
        // If there's any error with the fetch, show a generic alert
        alert('Error: ' + error.message);
        console.log('Error: ', error); // Log the error to the console for debugging
    });
}

function register() {
    // Get input values
    let username = document.getElementById('username').value;
    let regName = document.getElementById('first-name').value;
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirm-password').value;
    let role = document.getElementById('role').value;
    role = role.toUpperCase();
    if (role == "PROFESOR")
        role = "TEACHER";

    // Check if passwords match
    if (password !== confirmPassword) {
        alert('Passwords do not match!');
        return;
    }

    // Prepare data for the request body
    let data = {
        username: username,
        password: password,
        name: regName,
        role: role
    };

    // Send POST request to /api/users/register
    fetch('/api/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // Indicate that we are sending JSON
        },
        body: JSON.stringify(data) // Send data as JSON string
    })
    .then(response => response.text()) // Get the response as plain text
    .then(responseText => {
        try {
            // Try parsing the response as JSON
            let data = JSON.parse(responseText);
            
            // If data is successfully parsed, handle it as a successful login
            localStorage.setItem('name', data.name);
            localStorage.setItem('username', data.username);
            localStorage.setItem('role', data.role);

            window.location.href = 'home.html';
        } catch (error) {
            // If parsing fails, assume it's a plain error message
            alert('Error: ' + responseText); // Display the error message returned by the API
            console.log('Error: ', responseText); // Log the error to the console for debugging
        }
    })
    .catch(error => {
        // If there's any error with the fetch, show a generic alert
        alert('Error: ' + error.message);
        console.log('Error: ', error); // Log the error to the console for debugging
    });
}