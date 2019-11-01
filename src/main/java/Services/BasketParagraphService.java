package Services;

import Dao.BasketParagraphDao;
import Dao.IBasketParagraphDao;
import org.springframework.stereotype.Service;

@Service("BasketParagraphService")
public class BasketParagraphService implements IBasketParagraphService {

    IBasketParagraphDao myBp;

    public BasketParagraphService(IBasketParagraphDao bpd) {
        myBp = bpd;
    }
    @Override
    public void deleteBasketParagraph(Long bpId) {

    }

    @Override
    public void editNumberOfBooks(Long bdId, String plusOrMinus) {

    }
}
