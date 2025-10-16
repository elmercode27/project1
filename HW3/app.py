from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def form():
    return render_template('form.html')

@app.route('/profile', methods=['POST'])
def profile():
    firstname = request.form.get('firstname')
    lastname = request.form.get('lastname')
    sex = request.form.get('sex')
    status = request.form.get('status')
    location = request.form.get('location')

    return render_template('profile.html', firstname=firstname, lastname=lastname, sex=sex, status=status, location=location)

if __name__ == '__main__':
    app.run(debug=True)
