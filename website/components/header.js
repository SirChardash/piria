import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import Link from 'next/link';
import {useKeycloak} from "@react-keycloak/ssr";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import getUserId from "../lib/userId";
import {logEvent} from "../lib/tracking";

export default function Header() {
    const buttonStyle = {color: 'lavender', fontWeight: 'bolder', fontSize: '12pt'}
    const adminButtonStyle = {color: 'lightcoral', fontWeight: 'bolder', fontSize: '12pt'}
    const {keycloak, initialized} = useKeycloak()
    const {locale} = useRouter()
    const l10n = fullL10n[locale].header

    const userButtons = initialized
        ? (keycloak?.authenticated
            ? <Link href={'/signOut'}>
                <Button style={buttonStyle} onClick={() => logNavigation('signOut')}>
                    {l10n.signOut}
                </Button>
            </Link>
            : <Link href={'/signIn'}>
                <Button style={buttonStyle}>
                    {l10n.signIn}
                </Button>
            </Link>)
        : <div/>

    const adminButtons = initialized && keycloak?.authenticated && keycloak?.realmAccess?.roles.includes('admin')
        ? <>
            <Link href={'/admin/messages'}>
                <Button style={adminButtonStyle}>
                    {l10n.messages}
                </Button>
            </Link>
            <Link href={'/admin/museums'}>
                <Button style={adminButtonStyle}>
                    {l10n.addMuseums}
                </Button>
            </Link>
            <Link href={'/admin/stats'}>
                <Button style={adminButtonStyle}>
                    {l10n.stats}
                </Button>
            </Link>
            <Link href={'/admin/users'}>
                <Button style={adminButtonStyle}>
                    {l10n.users}
                </Button>
            </Link>
        </>
        : <div/>

    const username = initialized && keycloak.authenticated
        ? <Link href={'/profile'}><Typography style={{color: '#babfc4'}}>
            {l10n.welcome}, {keycloak?.idTokenParsed?.name}
        </Typography></Link>
        : <Box/>

    return (
        <Box sx={{flexGrow: 1}}>
            <AppBar position="static" style={{backgroundColor: '#24292F'}}>
                <Toolbar>
                    <Link href={'/'}>
                        <IconButton size="large" edge="start" color="inherit" aria-label="menu" sx={{mr: 2}}>
                            ⚔️
                        </IconButton>
                    </Link>
                    <Link href={'/museums'} className={{color: 'lavender', fontWeight: 'bolder', fontSize: '12pt'}}>
                        <Button style={buttonStyle}
                                onClick={() => logNavigation('museums')}>{l10n.museums}</Button>
                    </Link>
                    <Link href={'/tours'} style={buttonStyle}>
                        <Button style={buttonStyle} onClick={() => logNavigation('tours')}>{l10n.tours}</Button>
                    </Link>
                    {adminButtons}
                    <Typography variant="h6" component="div" sx={{flexGrow: 1}}> </Typography>
                    {username}
                    {userButtons}
                </Toolbar>
            </AppBar>
        </Box>
    );

    function logNavigation(section) {
        logEvent(
            initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
            'header',
            null,
            'header navigation',
            section
        )
    }
}