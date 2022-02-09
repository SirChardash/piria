import Layout from "../components/layout";
import Box from '@mui/material/Box'
import Tabs from '@mui/material/Tabs'
import {Tab} from "@mui/material";
import {useState} from "react";
import Typography from "@mui/material/Typography";
import TourLoader from "../components/tourLoader";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function Tours() {
    const [value, setValue] = useState(0);
    const {locale} = useRouter()
    const l10n = fullL10n[locale].tours

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <Layout authenticated>
            <Box sx={{borderBottom: 1, borderColor: 'divider'}}>
                <Tabs value={value} onChange={(event, newValue) => handleChange(event, newValue)}>
                    <Tab label={l10n.pastMonth}/>
                    <Tab label={l10n.upcoming}/>
                    <Tab label={l10n.bookedTours}/>
                </Tabs>
            </Box>
            <TabPanel value={value} index={0}>
                <TourLoader endpoint={'http://localhost:8081/tours/previous'} noResultText={l10n.noRecentTours}/>
            </TabPanel>
            <TabPanel value={value} index={1}>
                <TourLoader endpoint={'http://localhost:8081/tours/upcoming'}
                            noResultText={l10n.noUpcomingTours}
                            canBook
                            context={'tours'}/>
            </TabPanel>
            <TabPanel value={value} index={2}>
                <TourLoader endpoint={'http://localhost:8081/tours/booked'}
                            noResultText={l10n.noBookedTours}
                            showStatus/>
            </TabPanel>
        </Layout>
    )
}

function TabPanel(props) {
    const {children, value, index, ...other} = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}>
            {value === index && (
                <Box sx={{p: 3}}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}