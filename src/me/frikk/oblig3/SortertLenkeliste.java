package me.frikk.oblig3;

class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {
    /**
     * Itererer gjennom listen og sammenligner elementene
     * Sørger for at elementer settes inn i sortert rekkefølge
     */
    @Override
    public void leggTil(T data) {
        if (stoerrelse() == 0) {
            super.leggTil(data);
            return;
        }

        for (int i = 0; i < stoerrelse(); i++) {
            if (hent(i).compareTo(data) > 0) {
                super.leggTil(i, data);
                return;
            }
        }

        super.leggTil(data);
    }

    /**
     * Fjerner det største elementet
     * @return elementet som fjernes
     */
    @Override
    public T fjern() {
        int index = 0;

        for (int i = 0; i < stoerrelse(); i++) {
            if (hent(i).compareTo(hent(index)) > 0) {
                index = i;
            }
        }

        return super.fjern(index);
    }

    @Override
    public void sett(int pos, T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}