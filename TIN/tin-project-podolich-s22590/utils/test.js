const authUtils = require('./authUtils');

console.log(authUtils.hashPassword('user1'))
const hashedPass1 = "$2a$08$fULcdbYHCF/s1EYswAusj.UHgdjui7kj3j0lEWpoS1V0Kw1tzCLHy";
console.log(authUtils.hashPassword('user2'))
const hashedPass2 = "$2a$08$fULcdbYHCF/s1EYswAusj.DY2vnEGzd6RvnoZ.kMW0s8PtUOt0Pna";
console.log(authUtils.hashPassword('user3'))
const hashedPass3 = "$2a$08$fULcdbYHCF/s1EYswAusj.aiLBcKkr.rB9bjGRUAKHZZuJ9D1/Il.";
console.log(authUtils.hashPassword('admin'))
const hashedPass4 = "$2a$08$fULcdbYHCF/s1EYswAusj.ZGb2m5hZBWmg.rX9BEeXndY.TN5BvRi";