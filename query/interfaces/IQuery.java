package chakri.query.interfaces;

import chakri.prompt.Result;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */



public interface IQuery {
    Result ExecuteQuery();
    boolean ValidateQuery();
}
