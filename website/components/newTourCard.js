import fullL10n from "../l10n";
import {Card, Grid, TextField} from "@mui/material";
import {useRouter} from "next/router";
import {useState} from "react";

export default function NewTourCard({state, onChange}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].addTour
    const [wtf, setWtf] = useState(true)

    return (
        <Grid container my={2}>
            <Card sx={{minWidth: 275}} variant={'outlined'}>
                    <Grid item key={'startTime'} xs={12} md={6} px={5} py={1}>
                        <TextField fullWidth
                                   label={l10n.startTime}
                                   variant="standard"
                                   value={state?.startTime}
                                   onChange={event => set(event, 'startTime')}/>
                    </Grid>
            </Card>
        </Grid>
    )

    function set(event, field) {
        state[field] = event.target.value
        onChange(state)
        setWtf(!wtf)
    }
}