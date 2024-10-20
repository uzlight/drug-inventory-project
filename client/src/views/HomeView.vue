<template>
  <div id="main-div" v-if="!isLoading">
    <choices-selection id="choices"/>
    <displays-table id="displays"/>
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
  components: {ChoicesSelection, DisplaysTable},
  data() {
    return {
      isLoading: false,
      error: null
    };
  },
  created() {
    this.isLoading = true;

    Promise.all([
      resourceService.getDrugs(), 
      resourceService.getClasses()
    ]).then(([drugResponse, resourceResponse]) => {
      this.$store.commit('SET_DRUGS', drugResponse.data);
      this.$store.commit('SET_CLASSES', resourceResponse.data);
    }).catch( (error) => {
      console.log(error);
    }).finally( () => {
      this.isLoading = false;
    }) ;

    this.$store.commit('SET_CHOICES', resourceService.getChoices());
  } 
}
</script>

<style>
#main-div {
    grid-area: Main;
    display: grid;
    background-color: rgb(198, 197, 185);
    padding: 10px;
    grid-template-columns: 1fr 2fr;
    grid-template-areas: "choices displays";
    overflow: auto;
}

#choices {
  grid-area: choices;
}

#displays {
  grid-area: displays;
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

    ul {
        flex-direction: column;
        align-items: center;
    }

    #main-div {
        flex-direction: column;
        grid-template-columns: 1fr;
        grid-template-areas:
         "choices" 
         "displays";
    }

    #image {
        max-width: 20vw;
    }

    h2 {
        font-size: 5vw;
    }

  }
</style>