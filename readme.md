# Business Process

1. All payments are supposed to be handled by the delivery service offline.
2. The customer (for now, it is a 3rd-party service) should be able to create and cancel booking requests.
3. Bookshop managers should review booking requests, approve, or reject them.
4. The delivery service will receive approved requests and deliver products accordingly.

# Use Cases

The web application should support 3 types of users: customers, managers, and administrators:

- **Customer**:
    - Should be able to view products, make, edit, and cancel their own product bookings and view their status.
    - Should also be able to register, view, edit, and delete their own user profile.

- **Manager**:
    - Should be able to manage products - create, view, edit, and delete products, product storage items.
    - Should also be able to manage all users' bookings.

- **Administrator**:
    - Should be able to manage users (e.g., create accounts for 3rd-party services).