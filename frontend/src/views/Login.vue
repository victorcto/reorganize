<template>
    <div id="container">
      <h2 class="title">Reorganize</h2>
      <form class="login-container" @submit.prevent="login()">
        <label for="username">Nome de usuário</label>
        <input type="text" placeholder="Informe o nome de usuário..." id="username" v-model="username" class="username-input" required>

        <label for="password">Senha</label>
        <input type="password" placeholder="Digite a senha" id="password" v-model="password" class="password-input" required>

        <input type="submit" value="Entrar" class="submit-button">

        <span @click="register()">Não tenho uma conta!</span>
      </form>
    </div>
</template>
<script>
export default{
  name: "Login",
  data(){
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    login(){
      this.$router.push({name: 'tasks'});
    },

    register(){
      this.$router.push({name: 'register'});
    }
  }
}
</script>
<style scoped>
#container{
  max-width: 500px;
  flex-basis: 400px;
  margin: 75px auto;
  overflow: auto;
  min-height: 300px;
  border: 2px solid chartreuse;
  padding: 30px;
  border-radius: 10px;
}

h2.title{
  color: #eee;
}

label {
  color: #eee;
  font-size: 12pt;
  font-weight: bold;
  align-self: flex-start;
}

span:hover{
  color: chartreuse;
}

span{
  padding-top: 20px;
  color: #eee;
  font-size: 9pt;
  cursor: pointer;
  text-decoration: none;
}

.login-container{
  box-sizing: border-box;
  padding: 15px;
  margin: 15px 0;
  width: 100%;
  display: flex;
  gap: 15px;
  flex-flow: column wrap;
}

input.username-input{
  height: 30px;
  padding: 0 10px;
  border-radius: 5px;
  border: none;
  background-color: #444;
  color: #eee;
  font-size: 16px;
}

input.password-input{
  height: 30px;
  padding: 0 10px;
  border-radius: 5px;
  border: none;
  background-color: #444;
  color: #eee;
  font-size: 16px;
}

input.username-input:focus{
  outline: none;
  border: 2px solid chartreuse
}

input.password-input:focus {
  outline: none;
  border: 2px solid chartreuse
}

.submit-button{
  align-self: center;
  margin-left: 10px;
  background-color: chartreuse;
  height: 40px;
  padding: 0 10px;
  border-radius: 5px;
  font-size: 16px;
  color: #444;
  font-weight: bold;
  cursor: pointer;
  border: none;
  margin-top: 20px;
}
</style>