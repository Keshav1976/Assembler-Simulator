from flask import Flask, render_template, request, redirect, url_for
import pymysql

app = Flask(__name__)
app.config['SECRET_KEY'] = "hoo"
@app.route('/')  
@app.route('/login',methods=['GET','POST'])
def login():
    if request.method == 'POST':
        # Retrieve user input
        username = request.form['username']
        password = request.form['password']
        host = "localhost"
        dbusername = "root"
        dbpassword = "Dwarka@612"
        dbname = "kissankart"

        # Connect to the database
        conn = pymysql.connect(host=host, user=dbusername, password=dbpassword, database=dbname)

        try:
            with conn.cursor() as cursor:
                # Query database
                query = "SELECT MobileNumber, Password FROM user WHERE MobileNUmber = %s AND Password = %s"
                cursor.execute(query, (username, password))
                result = cursor.fetchone()

                if result:
                    # Login successful
                    return redirect('/index')
                else:
                    # Incorrect username or password
                    return redirect('/loginerror')
        finally:
            conn.close()

    return render_template('loginHtlml.html')

@app.route('/signuptesting', methods=['POST'])
def signuptesting():
    if request.method == 'POST':
        # Retrieve user input from the signup form
        name = request.form['name']
        email = request.form['email']
        phone = request.form['phone']
        password = request.form['password']

        # Database connection details
        host = "localhost"
        dbusername = "root"
        dbpassword = "Dwarka@612"
        dbname = "kissankart"

        # Connect to the database
        conn = pymysql.connect(host=host, user=dbusername, password=dbpassword, database=dbname)

        try:
            with conn.cursor() as cursor:
                # Check if the user already exists in the database
                query = "SELECT * FROM user WHERE Email = %s OR MobileNumber = %s"
                cursor.execute(query, (email, phone))
                result = cursor.fetchone()

                if result:
                    # User already exists, show a message
                    return render_template('signuperror.html')

                else:
                    # Insert the user into the database
                    insert_query = "INSERT INTO user (FirstName, Email, MobileNumber, Password) VALUES (%s, %s, %s, %s)"
                    cursor.execute(insert_query, (name, email, phone, password))
                    conn.commit()

                    # Registration successful, redirect to login page
                    return redirect('/login')

        finally:
            conn.close()

    return render_template('signup.html')


@app.route('/forgot_password_new', methods=['GET', 'POST'])
def forgot_password():
    if request.method == 'POST':
        host = "localhost"
        dbusername = "root"
        dbpassword = "Dwarka@612"
        dbname = "kissankart"

        # Retrieve username and phone from the form
        username = request.form['username']
        phone = request.form['phone']

        conn = pymysql.connect(host=host, user=dbusername, password=dbpassword, database=dbname)

        try:
            with conn.cursor() as cursor:
                # Check if the user already exists in the database
                query = "SELECT * FROM user WHERE Email = %s AND MobileNumber = %s"
                cursor.execute(query, (username, phone))
                result = cursor.fetchone()

                if result:
                    # User already exists, show a message

                    return render_template('reset_password.html')
                
                else:
                        return render_template('forgeterror.html')


        finally:
            conn.close()

    return redirect(url_for('reset_password_form', username=username))

@app.route('/reset_password_form', methods=['GET','POST'])
def reset_password_form():
    if request.method == 'POST':
        host = "localhost"
        dbusername = "root"
        dbpassword = "Dwarka@612"
        dbname = "kissankart"

        # Retrieve username and new password from the form
        email = request.form['email']
        new_password = request.form['confirm_password']

        conn = pymysql.connect(host=host, user=dbusername, password=dbpassword, database=dbname)

        try:
            with conn.cursor() as cursor:
                # Check if the user already exists in the database
                query = "UPDATE user SET Password = %s WHERE Email = %s"
                cursor.execute(query, (new_password, email))
                conn.commit()
        finally:
            conn.close()
    return render_template('loginHtlml.html')



@app.route('/index')
def index():
    return render_template('home.html')

@app.route('/loginerror')
def login_error():
    return render_template('loginerror.html')

@app.route('/signup')
def signup():
    return render_template('signup.html')
@app.route('/shoppingcart')
def shoppingcart():
    return render_template('shoppingcart.html')

@app.route('/forget_password')
def forget_password():
    return render_template('forget_password.html')

@app.route('/forgeterror')
def forgeterror():
    return render_template('forgeterror.html')

@app.route('/reset_password')
def reset_password():
    return render_template('reset_password.html')
@app.route('/checkout')
def checkout():
    return render_template('checkout.html')

if __name__ == '__main__':
    app.run(debug=True)
