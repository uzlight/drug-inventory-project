<template>
  <div id="main-div" v-if="!isLoading">
    <choices-selection />
    <displays-table />
  </div>
  <div v-else>
    Loading...
  </div>
</template>

<script>
import ChoicesSelection from '../components/ChoicesSelection.vue';
import DisplaysTable from '../components/DisplaysTable.vue';
import { resourceService } from '../services/ResourceService.js';

export default {
  components: {
    ChoicesSelection,
    DisplaysTable,
  },
  data() {
    return {
      isLoading: false,
    };
  },
  created() {
    this.isLoading = true;
    Promise.all([
      resourceService.getDrugs(),
      resourceService.getClasses()
    ]).then(([drugsResponse, classesResponse]) => {
      this.$store.commit('SET_DRUGS', drugsResponse.data);
      this.$store.commit('SET_CLASSES', classesResponse.data);
    }).catch(error => {
      console.error('Failed to fetch data:', error);
    }).finally(() => {
      this.isLoading = false;
    });
    const choices = resourceService.getChoices();
    this.$store.commit('SET_CHOICES', choices);
  }
}
</script>

<style>
#main-div {
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-areas: "choices displays";
    background-color: rgb(198, 197, 185);
    padding: 10px;
    overflow: auto;
}

@media (max-width: 425px) {
    #main-div {
        grid-template-columns: 1fr;
        grid-template-areas:
         "choices"
         "displays";
    }
}
</style>
