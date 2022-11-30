import fullL10n from "../l10n";
import {Card, FormControlLabel, Grid, Radio, RadioGroup, TextField} from "@mui/material";
import {useRouter} from "next/router";
import {useState} from "react";
import Button from "@mui/material/Button";
import axios from "axios";
import endpoints from "../endpoints";
import {useKeycloak} from "@react-keycloak/ssr";

export default function NewTourCard({state, onChange, museumId}) {
    const router = useRouter()
    const locale = router.locale
    const {keycloak, initialized} = useKeycloak()
    const l10n = fullL10n[locale].addTour
    const [wtf, setWtf] = useState(true)
    const [videoUploadMode, setVideoUploadMode] = useState('file') // file, link

    const dateInput = <input type="datetime-local"
                             style={{display: 'inline-block', height: "2.8rem", fontSize: "1.2em"}}
                             onChange={event => setStartDate(event)}/>
    const videoInputRadio = <RadioGroup defaultValue="file" row
                                        onChange={event => setVideoUploadMode(event.target.value)}>
        <FormControlLabel value="file" control={<Radio/>} label="File"/>
        <FormControlLabel value="link" control={<Radio/>} label="Link"/>
    </RadioGroup>

    const videoInput = videoUploadMode === 'file'
        ? <input type='file' name='video' onChange={event => setVideo(event, "video")}/>
        : <TextField fullWidth
                     label={l10n.videoLink}
                     variant="standard"
                     value={state?.link}
                     onChange={event => set(event, 'link')}/>
    return (
        <Grid container my={2}>
            <Card sx={{minWidth: 275}} variant={'outlined'}>
                <form>
                    <Grid container my={3}>
                        <Grid item key={'startTime'} xs={12} md={6} px={5} py={1}>
                            <label style={{paddingRight: 20}}>{l10n.startTime}</label>
                            {dateInput}
                        </Grid>
                        <Grid item key={'duration'} xs={12} md={6} px={5} py={1}>
                            <TextField fullWidth
                                       label={l10n.duration}
                                       variant="standard"
                                       type='number'
                                       value={state?.duration}
                                       onChange={event => setEndDate(event)}/>
                        </Grid>
                        <Grid item key={'title'} xs={12} mx={3}>
                            <TextField fullWidth
                                       label={l10n.title}
                                       variant="standard"
                                       value={state?.title}
                                       onChange={event => set(event, 'title')}/>
                        </Grid>
                        <Grid item key={'description'} xs={12} mx={3}>
                            <TextField fullWidth
                                       label={l10n.description}
                                       variant="standard"
                                       value={state?.description}
                                       multiline
                                       onChange={event => set(event, 'description')}/>
                        </Grid>
                        <Grid item key={'images'} xs={12} mx={3} py={3}>
                            <label style={{paddingRight: 20}}>{l10n.pictures}</label>
                            <input type='file' name='images' onChange={event => setPictures(event, "image")} multiple/>
                        </Grid>
                        <Grid item key={'ticketPrice'} xs={12} mx={3} py={3}>
                            <TextField label={l10n.ticketPrice}
                                       variant="standard"
                                       type='number'
                                       value={state?.ticketPrice}
                                       onChange={event => set(event, 'ticketPrice')}/>
                        </Grid>
                        <Grid item key={'video'} xs={12} mx={3} py={3}>
                            <label style={{paddingRight: 20}}>{l10n.video}</label>
                            {videoInputRadio}
                            {videoInput}
                        </Grid>
                        <Grid item key={'submit'} xs={12} mx={3} py={3}>
                            <Button variant={'contained'} fullWidth
                                    onClick={() => submit()}>{l10n.submit}</Button>
                        </Grid>
                    </Grid>
                </form>
            </Card>
        </Grid>
    )

    function submit() {
        if (!validate(state)) {
            return
        }

        const formData = new FormData()
        formData.append('title', state.title)
        formData.append('description', state.description)
        for (let i = 0; i < state.image.length; i++) {
            formData.append('image', state.image[i])
        }
        if (videoUploadMode === 'link') {
            formData.append('link', state.link)
        } else {
            formData.append('video', state.video)
        }
        formData.append('museumId', museumId)
        formData.append('startTime', new Date(state.startDate).toISOString())
        formData.append('endTime', new Date(state.endDate).toISOString())
        formData.append('ticketPrice', state.ticketPrice)

        if (initialized) {
            axios.post(
                endpoints.museumApp + '/tours',
                formData, {headers: {authorization: 'Bearer ' + keycloak.token, 'content-type': 'multipart/form-data'}})
                .then(response => {
                    router.push("/museum/" + museumId)
                }, reason => {
                    alert(reason)
                })
        }
    }

    function setPictures(event, field) {
        if (event.target.files && event.target.files[0]) {
            if (event.target.files.length < 5 || event.target.files.length > 10) {
                alert(l10n.error.badPictureCount)
                event.target.value = null
            } else {
                state[field] = event.target.files;
            }
        }
    }

    function setVideo(event, field) {
        if (event.target.files && event.target.files[0]) {
            state[field] = event.target.files[0];
            onChange(state)
            setWtf(!wtf)
        }
    }

    function set(event, field) {
        state[field] = event.target.value
        onChange(state)
        setWtf(!wtf)
    }

    function setStartDate(event) {
        state['startDate'] = new Date(event.target.value).getTime()
        onChange(state)
        setWtf(!wtf)
    }

    function setEndDate(event) {
        if (isNaN(event.target.value)) {
            return
        }

        state['endDate'] = new Date(event.target.value * 3_600_000 + new Date(state['startDate']).getTime()).getTime()
        onChange(state)
        setWtf(!wtf)
    }

    function validate(state) {
        if (!state.description) {
            alert(l10n.error.noDescription)
            return false
        }

        if (!state.image || state.image.length < 5 || state.image.length > 10) {
            alert(l10n.error.badPictureCount)
            return false
        }

        if (!state.startDate || !state.endDate) {
            alert(l10n.error.badDates)
            return false
        }

        if (!state.title) {
            alert(l10n.error.noTitle)
            return false
        }

        if (videoUploadMode === 'file' && !state.video) {
            alert(l10n.error.noVideoFile)
            return false
        }

        if (videoUploadMode === 'link' && !state.link) {
            alert(l10n.error.noVideoLink)
            return false
        }

        if (!state.ticketPrice) {
            alert(l10n.error.noTicketPrice)
            return false
        }

        return true
    }

}