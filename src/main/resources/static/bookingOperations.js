function updateBookingCardUI(bookingId, status) {
    // Find the card element by booking ID and update its status.
    // Assuming each card has a unique ID like `booking-card-${bookingId}`
    const bookingCard = document.getElementById('booking-card-' + bookingId);
    if (bookingCard) {
        const statusElement = bookingCard.querySelector('.booking-status');
        if (statusElement) {
            //statusElement.textContent = 'Status: ' + status;
            statusElement.innerHTML = '<strong>Status: </strong> ' + status;
        }
    } else {
        console.error('Booking card not found');
    }
}

function cancelBooking(bookingId) {
    const cancelStatus = {
        id: 4 // Status ID for "cancel"
    };

    $.ajax({
        type: "PUT",
        url: '/api/bookings/' + bookingId + '/status',
        contentType: "application/json",
        data: JSON.stringify(cancelStatus),
        success: () => {
            console.log("AJAX request completed successfully.");
            updateBookingCardUI(bookingId, "canceled");
            alert('Booking has been canceled successfully!');
        },
        error: () => {
            alert("Failed to cancel the booking");
        }
    });
}

function approveBooking(bookingId) {
    const approvedStatus = {
        id: 3 // Status ID for "approved"
    };

    $.ajax({
        type: "PUT",
        url: '/api/bookings/' + bookingId + '/status',
        contentType: "application/json",
        data: JSON.stringify(approvedStatus),
        success: () => {
            console.log("AJAX request completed successfully.");
            updateBookingCardUI(bookingId, "approved");
            alert('Booking has been approved successfully!');
        },
        error: () => {
            alert("Failed to approve the booking");
        }
    });
}

function rejectBooking(bookingId) {
    const rejectedStatus = {
        id: 2 // Status ID for "rejected"
    };

    $.ajax({
        type: "PUT",
        url: '/api/bookings/' + bookingId + '/status',
        contentType: "application/json",
        data: JSON.stringify(rejectedStatus),
        success: () => {
            console.log("AJAX request completed successfully.");
            updateBookingCardUI(bookingId, "rejected");
            alert('Booking has been rejected successfully!');
        },
        error: () => {
            alert("Failed to reject the booking");
        }
    });
}

async function editBooking(bookingId) {
 try {
        const response = await fetch("editModal.html");
        const modalHtml = await response.text();

        const modalContainer = document.createElement('div');
        modalContainer.innerHTML = modalHtml;
        document.body.appendChild(modalContainer);

        // Assuming modal.html includes Bootstrap modal structure
        $('#editBookingModal').modal('show');
        document.getElementById('saveChanges').setAttribute('data-booking-id', bookingId);

    } catch (error) {
        console.error('Error:', error);
    }
try {
        const response = await fetch('/api/bookings/' + bookingId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const booking = await response.json(); // Await the parsing of the JSON

        document.getElementById('productTitle').value = booking.productTitle;
        document.getElementById('price').value = booking.price;
        document.getElementById('date').value = booking.date;
        document.getElementById('time').value = booking.time;
        document.getElementById('address').value = booking.deliveryAddress;

    } catch (error) {
        console.error('Failed to get Booking:', error);
    }
    setupSaveChangesListener()
}

function setupSaveChangesListener() {
    const saveChangesButton = document.getElementById('saveChanges');
    if (saveChangesButton) {
        saveChangesButton.addEventListener('click', async function () {
            const bookingId = this.getAttribute('data-booking-id');  // Ensure this attribute is set somewhere relevant
                    const updatedDate = document.getElementById('date').value;
                    const updatedTime = document.getElementById('time').value;
                    const updatedAddress = document.getElementById('address').value;

                    const updatedBooking = {
                        date: updatedDate,
                        time: updatedTime,
                        deliveryAddress: updatedAddress
                        // include other properties as needed
                    };

                    try {
                        const response = await fetch('/api/bookings/' + bookingId, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(updatedBooking),
                            credentials: 'include'
                        });

                        if (!response.ok) {
                            throw new Error('Failed to update booking');
                        }

                        $('#editBookingModal').modal('hide');
                        // Optionally refresh the data on the page or display a success message

                    } catch (error) {
                        console.error('Error:', error);
                    }
        });
    } else {
        console.error('Save Changes button not found');
    }
}

