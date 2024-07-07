import mysql.connector

# Connect to the database
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="Dwarka@612",
    database="kissankart"
)
cursor = db.cursor()
def display_products():
    try:
        cursor.execute("SELECT ProductId, Name, Availability, Price FROM Products")
        products = cursor.fetchall()
        print("Product ID\tProduct Name\t\tAvailability\t\tPrice")
        for product in products:
            # Adjust spacing based on the length of the strings
            product_id = str(product[0])
            name = product[1]
            availability = str(product[2])
            price = str(product[3])

            # Calculate additional spacing for alignment
            name_spacing = "\t" * (2 - (len(name) // 8))
            price_spacing = "\t" * (2 - (len(price) // 4))

            print(f"{product_id}\t\t{name}{name_spacing}\t\t{availability}\t\t{price}{price_spacing}")
    except mysql.connector.Error as e:
        print("Error:", e)

# Function to order items
def order_items():
    try:
        # Take input from the user
        user_id = int(input("Enter user ID: "))
        product_id = int(input("Enter product ID: "))
        quantity = int(input("Enter quantity: "))

        # Check if the product is available
        cursor.execute("SELECT Availability FROM Products WHERE ProductId = %s", (product_id,))
        availability = cursor.fetchone()[0]
        print(availability)
        if availability < quantity:
            print("Insufficient quantity available.")
            return

        # Calculate total price
        cursor.execute("SELECT Price FROM Products WHERE ProductId = %s", (product_id,))
        price = cursor.fetchone()[0]
        total_price = price * quantity

        # Add item to the shopping cart
        cursor.execute("INSERT INTO ShoppingCart (ProductId, ProductName, Quantity, TotalPrice, UserId) VALUES (%s, (SELECT Name FROM Products WHERE ProductId = %s), %s, %s, %s)",
                       (product_id, product_id, quantity, total_price, user_id))
        db.commit()
        print("Item added to cart successfully.")

        # Update the availability in the Products table
        new_availability = availability - quantity
        cursor.execute("UPDATE Products SET Availability = %s WHERE ProductId = %s", (new_availability, product_id))
        db.commit()
        print("Quantity updated in the Products table.")
    except mysql.connector.Error as e:
        print("Error:", e)


# Function for inventory or customer analysis
def analyze_data():
    try:
        # Example analysis: Get the total number of orders per user
        cursor.execute("SELECT u.UserId, u.FirstName, u.LastName, COUNT(b.BillId) AS TotalOrders FROM User u LEFT JOIN Bill b ON u.UserId = b.UserId GROUP BY u.UserId")
        results = cursor.fetchall()
        print("User ID \tName\tTotal Orders\tTotal Price\tShopping Cart")
        for row in results:
            user_id = row[0]
            first_name = row[1]
            last_name = row[2]
            total_orders = row[3]

            # Get shopping cart total price for the user
            cursor.execute("SELECT SUM(TotalPrice) FROM ShoppingCart WHERE UserId = %s", (user_id,))
            cart_total_price = cursor.fetchone()[0]

            # Get shopping cart items for the user
            cursor.execute("SELECT ProductName, Quantity FROM ShoppingCart WHERE UserId = %s", (user_id,))
            cart_items = cursor.fetchall()
            cart_display = ", ".join([f"{item[0]} x{item[1]}" for item in cart_items])

            print(f"{user_id}\t{first_name} {last_name}\t{total_orders}\t\t{cart_total_price}\t\t{cart_display}")
    except mysql.connector.Error as e:
        print("Error:", e)


display_products()
order_items()  
analyze_data() 

cursor.close()
db.close()