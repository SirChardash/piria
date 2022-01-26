import Box from "@mui/material/Box";
import {Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function MuseumProfile({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].museumProfile

    return (
        <Box py={1}>
            <Typography variant="h5" component="div">
                {data.name}
            </Typography>
            <Typography sx={{mb: 1.5}} color="text.secondary">
                {data.address + ', ' + data.city + ', ' + data.country}
            </Typography>
            <Typography sx={{mb: 1.5}} color="text.secondary">
                <strong>{l10n.category}: </strong>{data.museumType}
            </Typography>
            <Typography sx={{mb: 1.5}} color="text.secondary">
                <strong>{l10n.contact} </strong>{data.phoneNumber}
            </Typography>
            <Grid container height={500} py={5}>
                <iframe
                    src={data.googleLocation}
                    width="100%"
                    height="100%"
                    frameBorder="0"
                    style={{border: 0}}
                    allowFullScreen=""
                    aria-hidden="false"
                    tabIndex="0"
                />
            </Grid>
        </Box>
    )
}