/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    //do not forget to change this variable in new methods that change amount of real resumes!
    private int amountOfResumes = 0;

    void clear(){
        for (int i = amountOfResumes; i >= 0; i -= 1) {
            storage[i] = null;

        }
        amountOfResumes = 0;
    }

    void save(Resume r){
        for (int i = amountOfResumes; i >= 0; i -= 1) {
            if (i == 0) {
                storage[i] = r;
                break;
            }
            storage[i] = storage[i - 1];
        }
        amountOfResumes += 1;
    }

    Resume get(String uuid){
        for (int i = 0; i < amountOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid){
        int indexTODelete = 0;

        for (int i = 0; i < amountOfResumes; i++) {
            if (uuid.equals(storage[i].uuid)) {
                indexTODelete = i;
            }
        }

        if (amountOfResumes - indexTODelete >= 0)
            System.arraycopy(storage, indexTODelete + 1, storage, indexTODelete, amountOfResumes - indexTODelete);

        amountOfResumes -= 1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll(){
        Resume[] onlyResumes = new Resume[amountOfResumes];
        System.arraycopy(storage, 0, onlyResumes, 0, amountOfResumes);
        return onlyResumes;
    }

    int size(){
        return amountOfResumes;
    }


}

