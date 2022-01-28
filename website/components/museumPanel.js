import {FormControl, Grid, InputLabel, MenuItem, Select} from "@mui/material";
import Box from "@mui/material/Box";
import {useState} from 'react'
import MuseumList from "./museumList";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {logEvent} from "../lib/tracking";
import {useKeycloak} from "@react-keycloak/ssr";
import getUserId from "../lib/userId";

export default function MuseumPanel({museums}) {
    const [allMuseums] = useState(museums)
    const [countryFilterValue, setCountryFilterValue] = useState('All')
    const [cityFilterValue, setCityFilterValue] = useState('All')
    const [typeFilterValue, setTypeFilterValue] = useState('All')

    const {keycloak, initialized} = useKeycloak()

    const {locale} = useRouter()
    const l10n = fullL10n[locale].museumPanel

    const filteredMuseums = allMuseums
        .filter(entry => (countryFilterValue === 'All' || countryFilterValue === entry.country))
        .filter(entry => (cityFilterValue === 'All' || cityFilterValue === entry.city))
        .filter(entry => (typeFilterValue === 'All' || typeFilterValue === entry.museumType))

    return (
        <Grid container py={4}>
            <Grid item xs={12} sm={3} px={4} key={'filters'}>
                <p>{l10n.filterResults}</p>
                <DropdownFilter items={distinct(museums, 'country')}
                                label={l10n.country}
                                onChange={newValue => {
                                    setCountryFilterValue(newValue)
                                    logFilterChange('country', newValue)
                                }}
                                value={countryFilterValue}
                                key={'country'}/>
                <DropdownFilter items={distinct(museums, 'city')}
                                label={l10n.city}
                                onChange={newValue => {
                                    setCityFilterValue(newValue)
                                    logFilterChange('city', newValue)
                                }}
                                value={cityFilterValue}
                                key={'city'}/>
                <DropdownFilter items={distinct(museums, 'museumType')}
                                label={l10n.type}
                                onChange={newValue => {
                                    setTypeFilterValue(newValue)
                                    logFilterChange('type', newValue)
                                }}
                                value={typeFilterValue}
                                key={'museumType'}/>
            </Grid>
            <Grid item xs={12} sm={9} key={'museums'}>
                <MuseumList list={filteredMuseums}/>
            </Grid>
        </Grid>
    );

    function DropdownFilter({items, label, value, onChange}) {
        return (<Box py={2}>
            <FormControl fullWidth>
                <InputLabel id={label + '-select-label'}>{label}</InputLabel>
                <Select fullWidth label={'Type'} labelId={label + '-select-label'} variant={'standard'}
                        onChange={(event) => onChange(event.target.value)} value={value}>
                    <MenuItem value={'All'}>{l10n.all}</MenuItem>
                    {items.map(item => <MenuItem value={item} key={item}>{item}</MenuItem>)}
                </Select>
            </FormControl>
        </Box>)
    }

    function logFilterChange(type, newValue) {
        logEvent(
            initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
            'museums',
            'search filters',
            type + ' filter changed',
            newValue
        )
    }
}

function distinct(data, field) {
    return Array.from(new Set(data.map(entry => entry[field])))
}