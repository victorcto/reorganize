<template>
  <div id="container">
    <h2 class="title">Cadastrar Usuário</h2>

    <form class="register-container" @submit.prevent="register" action="#">
      <label for="username">Nome de usuário</label>
      <input type="text" id="username" placeholder="Nome de usuário..." class="username-input" v-model="username" required>

      <label for="email">Email</label>
      <input type="email" id="email" placeholder="Informe o email" class="email-input" v-model="email" required>

      <label for="password">Senha</label>
      <input type="password" id="password" placeholder="Senha..." class="password-input" v-model="password" required>

      <label for="password">Confirme a senha</label>
      <input type="password" id="password" placeholder="Confirme a senha..." class="password-input" v-model="passwordConfirm" required>

      <input type="submit" value="Cadastrar" class="register-button">

      <span @click="login()">Já tenho uma conta!</span>
    </form>
  </div>
</template>
<script>
export default {
  name: 'Register',
  data(){
    return {
      username: '',
      email: '',
      password: '',
      passwordConfirm: '',
    }
  },
  methods:{
    register(){
      if(this.password !== this.passwordConfirm){
        alert('A senha precisa ser igual à senha de confirmação');
        this.passwordConfirm = "";
        return;
      }
      console.log("CADASTROU");
    },

    login(){
      this.$router.push({name: 'login'})
    }
  }
}

</script>
<style scoped>
#container {
  max-width: 500px;
  flex-basis: 400px;
  margin: 75px auto;
  overflow: auto;
  min-height: 300px;
  border: 2px solid chartreuse;
  padding: 30px;
  border-radius: 10px;
}

h2.title {
  color: #eee;
}

span{
  padding-top: 10px;
  color: #eee;
  font-size: 9pt;
  cursor: pointer;
  text-decoration: none;
}

span:hover {
  color: chartreuse;
}

label{
  color: #eee;
  font-size: 12pt;
  font-weight: bold;
  align-self: flex-start;
}

.register-container{
  box-sizing: border-box;
  padding: 15px;
  margin: 15px 0;
  width: 100%;
  display: flex;
  gap: 15px;
  flex-flow: column wrap;
}

.username-input{
  height: 30px;
  padding: 0 10px;
  border-radius: 5px;
  border: none;
  background-color: #444;
  color: #eee;
  font-size: 16px;
}

.password-input{
  height: 30px;
  padding: 0 10px;
  border-radius: 5px;
  border: none;
  background-color: #444;
  color: #eee;
  font-size: 16px;
}

.email-input{
  height: 30px;
  padding: 0 10px;
  border-radius: 5px;
  border: none;
  background-color: #444;
  color: #eee;
  font-size: 16px;
}

.register-button{
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

.username-input:focus{
  outline: none;
  border: 2px solid chartreuse
}

.password-input:focus{
  outline: none;
  border: 2px solid chartreuse;
}

.email-input:focus{
  outline: none;
  border: 2px solid chartreuse;
}
</style>