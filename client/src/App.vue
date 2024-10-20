<template>
  <div id="drug-app">
    <header>
      <div id="header-div">
        <img id="image" alt="Simple Medication Log" src="./assets/drug-dealer-cartoon.png" >
        <h1>Simple Medication Log</h1>
      </div>
      <nav>

        <router-link v-bind:to="{ name: 'home' }">
          <button>Home</button>
        </router-link>

        <!-- <router-link v-bind:to="{ name: 'home' }">
          <button>Suggestions</button>
        </router-link>

        <router-link v-bind:to="{ name: 'home' }">
          <button>Contact Us</button>
        </router-link> -->

        <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
          <button>Logout</button>
        </router-link>

        <router-link v-bind:to="{ name: 'login' }" v-else>
          <button>Login</button>
        </router-link>

      </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      &copy; 2024. All Rights Reserved.
    </footer>
  </div>
</template>

<style>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

#drug-app {
    background-color: rgba(228, 220, 190, 0.357);
    height: 100vh;
    display: grid;
    grid-template-rows: auto 1fr auto;
    grid-template-areas: 
        "Header"
        "Main"
        "Footer";
    gap: 10px;
}

header {
    grid-area: Header;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: rgb(198, 197, 185);
    border-radius: 7px;
    padding: 20px;
}

#header-div {
    display: flex;
    flex-direction: row;
    align-items: center;
}

main {
    grid-area: Main;
    background-color: rgb(198, 197, 185);
    overflow: auto;
}

h1 {
    font-size: 4rem;
    color: beige;
    -webkit-text-stroke: 1px black;
}

#image {
    max-width: 6rem;
    /* height: auto; */
}

nav {
    display: flex;
    gap: 12px;
}

nav a {
    text-decoration: none;
    font-size: 1rem;
    /* color: rgb(83, 83, 83); */
}

nav a:hover {
    color: rgb(50, 50, 50);
}

nav button {
    list-style: none;
    padding: 5px;
    align-items: flex-end;
    cursor: pointer;
}

footer {
    grid-area: Footer;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgb(198, 197, 185);
    border-radius: 7px;
    padding: 15px;
    font-size: 1rem;
    color: rgb(83, 83, 83);
}

@media (max-width: 425px) {
    h1 {
        font-size: 6vw;
    }

    nav {
        flex-grow:1;
    }

    nav a {
        display: flex;
        font-size: 2.5vw;
    }

    #image {
        max-width: 20vw;
    }

    h2 {
        font-size: 5vw;
    }
  }
</style>