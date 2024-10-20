<template>
  <div id="register">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          placeholder="Name"
          v-model="user.name"
          required
        />
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <label for="confirmPassword">Confirm password</label>
        <input
          type="password"
          id="confirmPassword"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />

        <label for="address">Address</label>
        <input
          type="text"
          id="address"
          placeholder="Address"
          v-model="user.address"
        />

        <label for="city">City</label>
        <input type="text" id="city" placeholder="City" v-model="user.city" />

        <label for="state">State</label>
        <input
          type="text"
          id="state"
          placeholder="State"
          v-model="user.stateCode"
          maxlength="2"
          required
        />

        <label for="zip">ZIP</label>
        <input
          type="number"
          id="zip"
          placeholder="ZIP"
          v-model="user.zip"
          required
          minlength="5"
          maxlength="5"
        />
        <div></div>
        <div>
          <button type="submit">Create Account</button>
        </div>
      </div>
      <hr />
      Have an account?
      <router-link v-bind:to="{ name: 'login' }">Sign in!</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                // Show the validation errors
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
#register-container {
  background-color: rgb(198, 197, 185);
  border-radius: 10px;
  margin: 50px auto;
  width: 80%;
  max-width: 500px;
  padding: 20px;
}

h1 {
  text-align: center;
  font-size: 2.5rem;
  color: beige;
  -webkit-text-stroke: 1px black;
}

#fields {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

label {
  font-size: 1.2rem;
  color: rgb(83, 83, 83);
}

input {
  padding: 10px;
  font-size: 1rem;
  border-radius: 5px;
  border: 1px solid #ccc;
}

button {
  background-color: rgb(198, 197, 185);
  padding: 10px 20px;
  font-size: 1.2rem;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: rgb(160, 160, 150);
}

hr {
  margin: 20px 0;
  border: 1px solid rgb(83, 83, 83);
}

router-link {
  color: rgb(31, 168, 70);
  font-weight: bold;
  cursor: pointer;
  text-decoration: none;
}

router-link:hover {
  text-decoration: underline;
}


@media (max-width: 425px) {

  
  #register-container {
    width: 95%;
    max-width: none;
  }

  h1 {
    font-size: 1.8rem;
  }

  input {
    font-size: 1rem;
    padding: 8px;
  }


  button {
    font-size: 1rem;
    padding: 8px 15px;
  }
}

</style>
