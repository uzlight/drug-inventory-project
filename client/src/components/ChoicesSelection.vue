<template>
  <div id="choices">
    <ul>
      <li v-for="choice in choices" :key="choice.id" @click="selected(choice.id)">
        {{ choice.name }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  computed: {
    choices() {
      return this.$store.state.choices;
    },
  },
  methods: {
    selected(choiceId) {
      this.$store.commit('SET_CURRENT_CHOICE_ID', choiceId);
    },
  },
}
</script>

<style>
#choices {
  background-color: darkkhaki;
  padding: 10px;
  border-radius: 10px;
  margin: 1px;
  width: 100%;
}
li {
  font-size: 45px;
  color: rgb(83, 83, 83);
}
li:hover {
  color: rgb(31, 168, 70);
  cursor: pointer;
}
</style>