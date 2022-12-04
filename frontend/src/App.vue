<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/tasks">Tarefas</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/> -->
  <RouterView></RouterView>
  <!-- <tasks></tasks> -->
</template>

<script>
export default {
  name: "App",
  components: {
  },
};
</script>

<style>
body {
  margin: 0;
  background-color: #222;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}

#app {
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
