import {FormControl, Grid, InputLabel, MenuItem, Select} from "@mui/material";
import Box from "@mui/material/Box";
import {useState} from 'react'
import MuseumList from "./museumList";

export default function MuseumPanel({museums}) {
    const [allMuseums] = useState(museums)
    const [countryFilterValue, setCountryFilterValue] = useState('All')
    const [cityFilterValue, setCityFilterValue] = useState('All')
    const [typeFilterValue, setTypeFilterValue] = useState('All')

    const filteredMuseums = allMuseums
        .filter(entry => (countryFilterValue === 'All' || countryFilterValue === entry.country))
        .filter(entry => (cityFilterValue === 'All' || cityFilterValue === entry.city))
        .filter(entry => (typeFilterValue === 'All' || typeFilterValue === entry.museumType))

    return (
        <Grid container py={4}>
            <Grid item xs={12} sm={3} px={4} key={'filters'}>
                <p>Filter results</p>
                <DropdownFilter items={distinct(museums, 'country')}
                                label={'Country'}
                                onChange={newValue => setCountryFilterValue(newValue)}
                                value={countryFilterValue}
                                key={'country'}/>
                <DropdownFilter items={distinct(museums, 'city')}
                                label={'City'}
                                onChange={newValue => setCityFilterValue(newValue)}
                                value={cityFilterValue}
                                key={'city'}/>
                <DropdownFilter items={distinct(museums, 'museumType')}
                                label={'Type'}
                                onChange={newValue => setTypeFilterValue(newValue)}
                                value={typeFilterValue}
                                key={'museumType'}/>
            </Grid>
            <Grid item xs={12} sm={9} key={'museums'}>
                <MuseumList list={filteredMuseums}/>
            </Grid>
        </Grid>
    );
}

function distinct(data, field) {
    return Array.from(new Set(data.map(entry => entry[field])))
}

function DropdownFilter({items, label, value, onChange}) {
    return (<Box py={2}>
        <FormControl fullWidth>
            <InputLabel id={label + '-select-label'}>{label}</InputLabel>
            <Select fullWidth label={'Type'} labelId={label + '-select-label'} variant={'standard'}
                    onChange={(event) => onChange(event.target.value)} value={value}>
                <MenuItem value={'All'}>All</MenuItem>
                {items.map(item => <MenuItem value={item} key={item}>{item}</MenuItem>)}
            </Select>
        </FormControl>
    </Box>)
}
