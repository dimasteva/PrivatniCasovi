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
    .then(response => {
        if (response.ok) {
            return response.json(); // Parse the response as JSON if the request is successful
        } else {
            throw new Error('Incorrect username or password');
        }
    })
    .then(data => {
        // If the response is valid JSON, redirect to home page
        if (data) {
            localStorage.setItem('name', data.name);
            localStorage.setItem('username', data.username);
            localStorage.setItem('role', data.role);

            window.location.href = 'home.html';
        }
    })
    .catch(error => {
        // If there's an error, show an alert with the error message
        alert('Error: ' + error.message);
        console.log('Error: ', error); // Log the error to the console for debugging
    });
}


function register() {
    // Get input values
    let username = document.getElementById('username').value;
    let regName = document.getElementById('first-name').value;
    let password = document.getElementById('password').value; // Changed to match the correct input ID
    let confirmPassword = document.getElementById('confirm-password').value;
    let role = document.getElementById('role').value;
    role = role.toUpperCase();

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
    .then(response => {
        if (response.ok) {
            return response.json(); // Parse the response as JSON if the request is successful
        } else {
            throw new Error('Error during registration');
        }
    })
    .then(data => {
        // If the response is valid JSON, redirect to home page
        if (data) {
            localStorage.setItem('name', data.name);
            localStorage.setItem('username', data.username);
            localStorage.setItem('role', data.role);
            
            window.location.href = 'home.html';
        }
    })
    .catch(error => {
        // If there's an error, show an alert with the error message
        alert('Error: ' + error.message);
        console.log('Error: ', error); // Log the error to the console for debugging
    });
}

