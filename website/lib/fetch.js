const fetcher = async (url, token) => {
    const res = token
        ? await fetch(url, authHeader(token))
        : await fetch(url)


    if (!res.ok) {
        const error = new Error('An error occurred while fetching the data.')
        error.status = res.status

        throw error
    }

    return res.json()
}

function authHeader(token) {
    return {
        headers: {
            Authorization: 'Bearer ' + token
        }
    }
}

export default fetcher