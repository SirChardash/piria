import MuseumCard from "./museumCard";

export default function MuseumList({list}) {
    return (
        <>
            {list.length === 0
                ? <p>No results. Try searching for something else or refine your filters.</p>
                : list.map(museum => <MuseumCard key={museum.masterId} data={museum}/>)}
        </>
    )
}