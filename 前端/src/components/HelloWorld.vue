<template>
  <div id="example-1">
    <h1>{{ msg }}</h1>
    <div>
      <input id="account" placeholder="account" v-model="recontent1"></input>
      <input id="asset_value" placeholder="asset_value" v-model="recontent2"></input>
      <button id="Register" v-on:click="do_Register()">Register</button>
    </div>
    <div>
      <input id="account" placeholder="account" v-model="secontent1"></input>
      <button id="select" v-on:click="do_select()">select</button>
    </div>
    <div>
      <input id="from_account" placeholder="from_account" v-model="Trcontent1"></input>
      <input id="to_account" placeholder="to_account" v-model="Trcontent2"></input>
      <input id="amount" placeholder="amount" v-model="Trcontent3"></input>
      <button id="Transfer" v-on:click="do_Transfer()">Transfer</button>
    </div>
    <div>
      <button id="Settlement" v-on:click="do_Settlement()">Settlement</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data: function() {
    return {
      msg: 'Welcome to FISCO BCOS',
      recontent1: "",
      recontent2: "",
      secontent1: "",
      Trcontent1: "",
      Trcontent2: "",
      Trcontent3: ""
    };
  },
  methods: {
    do_Register: function() {

     },
    do_select: function() {

     },
    do_Transfer: function() {

     },
    do_Settlement: function() {

     }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1{
  font-weight: normal;
}
div{
  margin-top: 20px;
}
</style>
