<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
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
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <div><button type="submit">Sign in</button></div>
      </div>
      <hr/>
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
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
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
#login-container {
  background-color: rgb(198, 197, 185);
  border-radius: 10px;
  margin: 50px auto;
  width: 80%;
  max-width: 400px;
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
  #login-container {
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
