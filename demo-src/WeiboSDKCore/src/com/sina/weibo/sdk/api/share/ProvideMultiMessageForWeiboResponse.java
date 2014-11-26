/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;

import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.constant.WBConstants;

/**
 * 该类封装了微博向第三方应用请求时的多条响应数据。
 * @see {@link IWeiboShareAPI#sendResponse(BaseResponse)}
 * 
 * @author SINA
 * @since 2013-10-29
 */
public class ProvideMultiMessageForWeiboResponse extends BaseResponse {
    public WeiboMultiMessage multiMessage;

    public ProvideMultiMessageForWeiboResponse() {
    }

    public ProvideMultiMessageForWeiboResponse(Bundle bundle) {
        fromBundle(bundle);
    }

    @Override
    public int getType() {
        return WBConstants.COMMAND_FROM_WEIBO;
    }

    @Override
    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.multiMessage = new WeiboMultiMessage(bundle);
    }

    @Override
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(multiMessage.toBundle(bundle));
    }

    @Override
    final boolean check(Context context, VersionCheckHandler handler) {
        if (null == this.multiMessage) {
            return false;
        }

        if (handler != null) {
            if (!handler.checkResponse(context, 
                    reqPackageName, this.multiMessage)) {
                return false;
            }
        }

        return this.multiMessage.checkArgs();
    }
}