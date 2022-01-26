import Layout from "../components/layout";
import {Grid, InputAdornment, TextField} from "@mui/material";
import {Search} from "@mui/icons-material";
import Button from "@mui/material/Button";
import {useRouter} from 'next/router'
import {useState} from "react";
import MuseumLoader from "../components/museumLoader";

export default function Museums() {
    const [searchQuery, setSearchQuery] = useState(undefined)

    const router = useRouter()
    if (router.isReady && searchQuery === undefined && router.query.s !== undefined) {
        setSearchQuery(router.query['s'])
    }

    const searchUrl = 'http://localhost:8081/museums?query=' + searchQuery + '&language=sr'
    const searchField = <TextField fullWidth
                                   defaultValue={searchQuery}
                                   name='s'
                                   placeholder={'Search for museums'}
                                   InputProps={
                                       {
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <Search/>
                                               </InputAdornment>
                                           )
                                       }
                                   }
    />

    return (
        <Layout>
            <Grid container justifyContent={'space-around'}>
                <Grid item xs={10} sm={6} key={'museums'}>
                    <form onSubmit={(event) => {
                        event.preventDefault()
                        router.query['s'] = event.target.s.value
                        router.push({
                                pathname: '/museums/',
                                query: {...router.query}
                            },
                            undefined,
                            {}
                        )
                        setSearchQuery(event.target.s.value)
                    }}>
                        {searchField}
                        <Button type={"submit"} size={'large'}>Search</Button>
                    </form>
                </Grid>
            </Grid>
            <Grid container justifyContent={'center'}>
                {searchQuery === undefined ? '' : <MuseumLoader endpoint={searchUrl}/>}
            </Grid>
        </Layout>
    );
}
