
async function fetchUserRole() {
    try {
        const response = await fetch('/api/users/role', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json(); // Await the parsing of the JSON
        const roles = data.user_role; // Access user_role from the JSON object
        console.log('User roles:', roles);
        return roles; // Return the roles so it can be used when this function is called
    } catch (error) {
        console.error('Failed to fetch user roles:', error);
    }
}


async function renderUserButtons(booking) {
    // Here, you can add logic based on roles received
    // For example:
    let buttonHTML = '';
    const userRole = await fetchUserRole();
    if (userRole === '[ROLE_MANAGER]') {
            buttonHTML +=
                '<button class="btn btn-success" onclick="approveBooking(' + booking.id + ')">Approve</button>' +
                '<button class="btn btn-danger" onclick="rejectBooking(' + booking.id + ')">Reject</button>' +
                '<button class="btn btn-primary" onclick="closeBooking(' + booking.id + ')">Close</button>';
        }
    if (userRole === '[ROLE_CUSTOMER]') {
      buttonHTML +=
                      '<button class="btn btn-primary" onclick="editBooking(' + booking.id + ')">Edit</button>' +
                      '<button class="btn btn-danger" onclick="cancelBooking(' + booking.id + ')">Cancel</button>';
    }
    return buttonHTML;

}