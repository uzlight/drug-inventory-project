<template>
  <section>
    <table id="display-table">
        <thead>
            <tr>
                <th id="drug">Drug</th>
                <th id="drug-class">Drug Class</th>
            </tr>
        </thead>
        <tbody id="display-table">
            <tr v-for="display in filteredDisplays" :key="display.drug_name || display.class_id">
                <td id="td-drug"><router-link :to="{ name: 'DrugDetail', params: { id: display.id } }"> {{ display.drug_name }}</router-link></td>
                <td id="td-class"><router-link :to="{ name: 'ClassDetail', params: { id: display.class_id } }">{{ display.classes_name }}</router-link></td>
            </tr>
        </tbody>
    </table>
  </section>
</template>

<script>
export default {
    computed: {
        filteredDisplays() {

            console.log("Drugs:", this.$store.state.drugs);
            console.log("Classes:", this.$store.state.classes);
            console.log("Current Choice ID:", this.$store.state.currentChoiceId);
            let filtered = [];

            if (this.$store.state.currentChoiceId === 1) {
                filtered = this.$store.state.drugs.map(drug => ({
                    drug_name: drug.drug_name,
                    classes_name: null,  // No class name for drugs
                    class_id: drug.class_id
                }));

            } else if (this.$store.state.currentChoiceId === 2) {
                filtered = this.$store.state.classes.map(cls => ({
                    classes_name: cls.classes_name,
                    drug_name: null,
                    class_id: cls.id
                }));
            } else {
                filtered = this.$store.state.drugs.filter(drug => drug.class_id == this.$store.state.currentChoiceId);
            }
            console.log("Filtered Results:", filtered);
            return filtered;

        }
    }
}
</script>

<style>
/* #displays, #choices {
    background-color: darkkhaki;
    padding: 10px;
    border-radius: 10px;
    margin: 1px;
    flex-grow: 1;
    width: 100%;
} */

#drug {
    padding-left: 10vw;
}

#drug-class {
    padding-left: 10vw;
}

#td-class {
    padding-left: 10vw;
}

#td-drug {
    padding-left: 5vw;
}
/* .drug-item {
    list-style: none;
    color: rgb(83, 83, 83);
    justify-content: center;
    text-decoration: none;
    font-size: 1.5vw;
}  */

th {
    color: rgb(83, 83, 83);
    font-size: 3vw;
}
</style>