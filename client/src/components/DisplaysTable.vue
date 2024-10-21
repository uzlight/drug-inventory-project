<template>
    <section>
      <table id="display-table">
        <thead>
          <tr v-if="getCurrentChoice === 1">
            <th>Drug</th>
          </tr>
          <tr v-if="getCurrentChoice === 2">
            <th>Drug Class</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="display in filteredDisplays" :key="display.id">
            <td>
              <router-link
                v-if="display.name && getCurrentChoice === 1"
                :to="{ name: 'DrugDetail', params: { id: display.id } }"
              >
                {{ display.name }}
              </router-link>
              <router-link
                v-else-if="display.name && getCurrentChoice === 2"
                :to="{ name: 'ClassDetail', params: { id: display.id } }"
              >
                {{ display.name }}
              </router-link>
            </td>
            <td>
              <router-link v-if="getCurrentChoice === 2"
                :to="{ name: 'EditDrugClass', params: { id: display.id } }"
              >
                {{ "edit" }}
              </router-link>
            </td>
            <!-- <td>
              <router-link
                v-if="display.name"
                :to="{ name: 'ClassDetail', params: { id: display.id } }"
              >
                {{ display.name }}
              </router-link>
            </td> -->
          </tr>
        </tbody>
      </table>
    </section>
  </template>
  
  
  <script>
export default {
  computed: {
    filteredDisplays() {
      console.log("Current choice ID:", this.$store.state.currentChoiceId);
      console.log("Drugs in store:", JSON.stringify(this.$store.state.drugs));
      console.log("Classes in store:", JSON.stringify(this.$store.state.classes));

      let filtered = [];
      const currentChoiceId = this.$store.state.currentChoiceId;
      if (currentChoiceId === 1) {
        console.log("hello", this.$store.state.drugs)
        filtered = this.$store.state.drugs;
      } else if (currentChoiceId === 2) {
        filtered = this.$store.state.classes.map(cls => ({
          id: cls.id,
          name: cls.name,
          // drug_name: null,
        }));
      } else {
        filtered = this.$store.state.drugs.filter(drug => drug.class_id === currentChoiceId);
      }
      console.log("Filtered results:", JSON.stringify(filtered));
      return filtered;
    },
    getCurrentChoice() {
      return this.$store.state.currentChoiceId;
    }
  }
}
</script>

  
  <style>
  #display-table th, #display-table td {
    padding: 10px;
  }
  </style>  