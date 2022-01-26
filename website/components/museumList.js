import MuseumCard from "./museumCard";

export default function MuseumList({list}) {
    return (
        <>
            {list.map(museum => <MuseumCard key={museum.masterId} data={museum}/>)}
        </>
    )
}