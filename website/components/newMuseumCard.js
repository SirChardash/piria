import {Card, Grid, TextField} from "@mui/material";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import CountryList from "./countryList";
import CityList from "./cityList";
import Typography from "@mui/material/Typography";
import {useState} from "react";

export default function NewMuseumCard({language, state, onChange}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].addMuseum
    const [wtf, setWtf] = useState(true)

    return (
        <Grid container my={2}>
            <Card sx={{minWidth: 275}} variant={'outlined'}>
                <Typography variant={'h6'} color={'text.secondary'} py={2}
                            px={2}>{l10n.language}: {language}</Typography>
                <Grid container my={3}>
                    <Grid item key={'name'} xs={12} md={6} px={5} py={1}>
                        <TextField fullWidth
                                   label={l10n.name}
                                   variant="standard"
                                   value={state?.name}
                                   onChange={event => set(event, 'name')}/>
                    </Grid>
                    <Grid item key={'address'} xs={12} md={6} px={5} py={1}>
                        <TextField fullWidth
                                   label={l10n.address}
                                   variant="standard"
                                   value={state?.address}
                                   onChange={event => set(event, 'address')}/>
                    </Grid>
                    <Grid item key={'country'} xs={12} md={6} px={5} py={1}>
                        <CountryList value={state.country}
                                     onChange={event => set(event, 'country')}/>
                    </Grid>
                    <Grid item key={'city'} xs={12} md={6} px={5} py={1}>
                        <CityList city={state.city} onChange={event => set(event, 'city')} country={state.country}/>
                    </Grid>
                    <Grid item key={'phoneNumber'} xs={12} md={6} px={5} py={1}>
                        <TextField fullWidth
                                   label={l10n.phoneNumber}
                                   variant="standard"
                                   value={state?.phoneNumber}
                                   onChange={event => set(event, 'phoneNumber')}/>
                    </Grid>
                    <Grid item key={'museumType'} xs={12} md={6} px={5} py={1}>
                        <TextField fullWidth
                                   label={l10n.museumType}
                                   variant="standard"
                                   value={state?.museumType}
                                   onChange={event => set(event, 'museumType')}/>
                    </Grid>
                    <Grid item key={'googleLocation'} xs={12} md={12} px={5} py={1}>
                        <TextField fullWidth
                                   label={l10n.googleLocation}
                                   variant="standard"
                                   value={state?.googleLocation}
                                   onChange={event => set(event, 'googleLocation')}/>
                    </Grid>
                </Grid>
            </Card>
        </Grid>
    )

    function set(event, field) {
        state[field] = event.target.value
        if (field === 'country') {
            state['city'] = ''
        }
        onChange(state)
        setWtf(!wtf)
    }

}